import {Component, OnInit} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConversationsService} from "../services/conversations.service";
import {ConversationModel} from "../models/ConversationModel";

@Component({
  selector: 'app-groups-modal',
  templateUrl: './groups-modal.component.html',
  styleUrls: ['./groups-modal.component.css']
})
export class GroupsModalComponent implements OnInit {

  newConversations: ConversationModel[];
  result: ConversationModel[];
  startIndex: number;
  endIndex: number;

  constructor(private modalService: NgbModal, private conversationService: ConversationsService) {
  }

  /**
   * Called on component init. Load available conversation groups.
   */
  ngOnInit() {
    this.startIndex = 0;
    this.endIndex = 5;
    this.getAllNewGroups();
  }

  /**
   * Inrese number of displayed new conversation groups.
   */
  loadMore() {
    this.endIndex += 5;
    this.result = this.newConversations.slice(this.startIndex, this.endIndex);
  }

  /**
   * Loads new conversation groups.
   */
  getAllNewGroups(): void {
    this.conversationService.getNewGroups().subscribe(
      data => {
        this.newConversations = data;
        this.result = this.newConversations.slice(this.startIndex, this.endIndex);
      });
  }

  /**
   * Add new group to user subscribed conversation group list.
   * @param group conversation group to add.
   */
  addGroup(group: ConversationModel) {
    console.log("Adding new group with name " + group.name);
    this.conversationService.addGroup(group);
    let index: number = this.result.indexOf(group);
    let indexConv: number = this.result.indexOf(group);
    delete this.result[index];
    delete this.newConversations[indexConv];
    this.startIndex = 0;
    this.endIndex = 5;
    this.getAllNewGroups();
  }
}
