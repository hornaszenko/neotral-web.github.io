import {Component, OnInit} from '@angular/core';
import {ConversationsService} from "../services/conversations.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConversationModel} from "../models/ConversationModel";

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  groupNameInputValue: '';

  constructor(private modalService: NgbModal, private conversationService: ConversationsService) {
  }

  ngOnInit() {
  }

  /**
   * Get data form input and create new group with selected name.
   * This group will be added to user subscribed groups list.
   */
  createGroup() {
    let conversation = new ConversationModel();

    if (this.isNameValid()) {
      conversation.name = this.groupNameInputValue;
      this.conversationService.createNewConversation(conversation);
      this.modalService.dismissAll();
    }
  }

  /**
   * Checks if group name is valid.
   */
  isNameValid() {
    return this.groupNameInputValue != null && this.groupNameInputValue.length >= 4;
  }
}
