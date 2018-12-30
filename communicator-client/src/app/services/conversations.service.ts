import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from 'rxjs';
import {ConversationModel} from "../models/ConversationModel";
import {MessageModel} from "../models/MessageModel";

const headers = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json'}
  )
};

@Injectable({
  providedIn: 'root'
})
export class ConversationsService {

  conversations: Observable<ConversationModel[]>;
  private _conversations: BehaviorSubject<ConversationModel[]>;
  private userConversations: ConversationModel[];

  constructor(private http: HttpClient) {
    this._conversations = <BehaviorSubject<ConversationModel[]>>new BehaviorSubject([]);
    this.conversations = this._conversations.asObservable();
  }

  /**
   * Get all conversations for currently logged in user.
   */
  getGroupsForCurrentUser() {
    console.log("Loading conversations for current user");
    this.http.get<ConversationModel[]>('http://localhost:8080/communicator/api/groups')
      .subscribe(data => {
        console.log("Conversations loaded");
        this.userConversations = data;
        this._conversations.next(this.userConversations);
      })
  }

  getNewGroups(): Observable<ConversationModel[]> {
    return this.http.get<ConversationModel[]>('http://localhost:8080/communicator/api/groups/new');
  }

  /**
   * Add user to new conversation group.
   *
   * @param group conversation group to add.
   */
  addGroup(group: ConversationModel) {
    console.log("Subscribing new conversation");
    this.http.post<ConversationModel>('http://localhost:8080/communicator/api/groups/add', group, headers)
      .subscribe(data => {
        this.userConversations.push(data);
        console.log("New conversation subscribed");
      }, () => console.log("Cannot subscribe new group"));
  }

  loadMessages(conversation: ConversationModel, size: number): Observable<MessageModel[]> {
    return this.http.get<MessageModel[]>('http://localhost:8080/communicator/api/groups/messages?id=' + conversation.id + '&size=' + size);
  }

  addMessage(conversation: ConversationModel, content: string) {
    console.log("Sending message with content: " + content);
    return this.http.post<MessageModel>('http://localhost:8080/communicator/api/groups/' + conversation.id + '/messages/add', content);
  }

  /**
   * Removes conversation for current user using conversation id.
   *
   * @param conversationId id of conversation.
   */
  removeConversation(conversationId: number) {
    console.log("Removing conversation with id: " + conversationId);
    this.http.delete<ConversationModel>('http://localhost:8080/communicator/api/groups/remove/' + conversationId)
      .subscribe(() => {
        this.userConversations.forEach((t, i) => {
          if (t.id === conversationId) {
            this.userConversations.splice(i, 1);
          }
          console.log("Conversation removed");
        });

        this._conversations.next(this.userConversations);
      }, () => {
        console.log("Conversation with id " + conversationId + " cannot be deleted.");
      });
  }

  /**
   * Creates new conversation group and add it to subscribed group list.
   *
   * @param conversation conversation to add.
   */
  createNewConversation(conversation: ConversationModel) {
    console.log("Creating new group with name: " + conversation.name);
    this.http.put<ConversationModel>('http://localhost:8080/communicator/api/groups/add', conversation, headers)
      .subscribe(data => {
        this.addGroup(data);
      })
  }
}
