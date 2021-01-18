import { Component, OnInit } from '@angular/core';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Visit} from '../../core/model/visit';
import {Physician} from '../../core/model/physician';
import {PhysicianService} from '../../core/service/physician.service';
import {VisitService} from '../../core/service/visit.service';

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html'
})
export class VisitComponent implements OnInit {

  faEdit = faEdit;
  faTrash = faTrash;

  physicians = [];
  visits = [];
  selectedVisit: Visit;

  constructor(private modalService: NgbModal,
              private physicianService: PhysicianService,
              private visitService: VisitService) { }

  ngOnInit() {
    this.loadPhysician();
    this.loadVisits();
  }

  private loadVisits() {
    this.visitService.getAll()
      .subscribe(
        (response: any) => {
          this.visits = response;
        },
        (error) => {
        });
  }

  private loadPhysician() {
    this.physicianService.getAll()
      .subscribe(
        (response: any) => {
          this.physicians = response;
        },
        (error) => {
        });
  }

  add(content) {
    this.selectedVisit = {};
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.visitService.add(this.selectedVisit)
        .subscribe(
          (response: any) => {
            this.loadVisits();
          },
          (error) => {
          });
    }, (reason) => {
    });
  }

  edit(content, visit: Visit) {
    this.selectedVisit = visit;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      // TODO call backend
    }, (reason) => {
    });
  }

  physicianCompare(o1: Physician, o2: Physician) {
    return o1 && o2 && (o1.id === o2.id);
  }

  delete(visit: Visit) {
    // TODO call backend
  }
}
