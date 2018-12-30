import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/AuthService";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {GroupsModalComponent} from "../groups-modal/groups-modal.component";
import {CreateGroupComponent} from "../create-group/create-group.component";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private authService: AuthService, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  /**
   * Opens add new conversation group modal.
   */
  open() {
    const modalRef = this.modalService.open(GroupsModalComponent);
    modalRef.componentInstance.name = 'Add Group Modal';
  }

  /**
   * Opens create new group conversation modal.
   */
  openCreateGroupModal() {
    const modalRef = this.modalService.open(CreateGroupComponent);
    modalRef.componentInstance.name = "Create new group";
  }
}
