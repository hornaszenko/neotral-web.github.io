import {Component, OnInit} from '@angular/core';
import {ConversationModel} from "../models/ConversationModel";
import {HttpClient} from "@angular/common/http";
import {ConversationsService} from "../services/conversations.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-conversations',
  templateUrl: './conversations.component.html',
  styleUrls: ['./conversations.component.css']
})
export class ConversationsComponent implements OnInit {

  conversations: Observable<ConversationModel[]>;
  selectedConversation: ConversationModel;
  size: number = 10;

  constructor(private http: HttpClient, private conversationService: ConversationsService) {

  }

  /**
   * Displays all user's conversations.
   */
  ngOnInit() {
    this.conversations = this.conversationService.conversations;
    this.conversationService.getGroupsForCurrentUser();
    console.log("Initialized conversation component");
    console.log(this.conversations);
  }

  resetSize() {
    this.size = 10;
  }

  /**
   * Selects conversation.
   *
   * @param conversation selected conversations.
   */
  selectConversation(conversation): void {
    this.selectedConversation = conversation;
  }

  /**
   * Removes conversation from the list.
   *
   * @param conversation conversation to remove.
   */
  remove(conversation: ConversationModel): void {
    this.conversationService.removeConversation(conversation.id);
  }
}
