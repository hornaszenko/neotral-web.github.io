import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ConversationModel} from "../models/ConversationModel";
import {ConversationsService} from "../services/conversations.service";
import {MessageModel} from "../models/MessageModel";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnChanges, AfterViewInit {

  @Input() conversation: ConversationModel;
  @Input() size: number;
  textInputValue = '';
  messages: MessageModel[];

  constructor(private conversationService: ConversationsService) {
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.scrollBottom();
  }

  /**
   * Load 10 more messages to front.
   */
  loadMore() {
    this.size += 10;
    this.loadMessages(this.conversation, this.size);
  }

  /**
   * Load messages when user choose different conversation.
   */
  ngOnChanges(changes: SimpleChanges): void {
    if (this.conversation) {
      this.loadMessages(this.conversation, this.size);
    }
  }

  /**
   * Loads messages.
   * @param group conversation which messages should be loaded.
   * @param size number of messages to load.
   */
  loadMessages(group: ConversationModel, size: number) {
    this.conversationService.loadMessages(group, size).subscribe(
      data => {
        this.messages = data;
      });
  }

  /**
   * Send message.
   */
  sendMessage(): void {
    console.log("Message add: " + this.textInputValue);
    if (this.textInputValue.length != 0) {
      this.conversationService.addMessage(this.conversation, this.textInputValue).subscribe(
        (date: MessageModel) => {
          this.messages.push(date);
          this.textInputValue = '';
        }
      )
    }
  }

  /**
   * Scroll to the bottom of messages window.
   */
  scrollBottom(): void {
    let div = document.getElementById('messages');
    if (div != null) {
      div.scrollTop = div.scrollHeight - div.clientHeight;
    }
  }
}
