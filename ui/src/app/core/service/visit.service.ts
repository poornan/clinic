import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Visit} from '../model/visit';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  VISIT_URI = environment.baseURL + 'visits';

  constructor(
    private http: HttpClient) {
  }

  getAll() {
    return this.http.get(this.VISIT_URI);
  }

  add(visit: Visit) {
    return this.http.post(this.VISIT_URI, visit);
  }

  edit(visit: Visit) {
    return this.http.put(this.VISIT_URI, visit);
  }

  delete(visit: Visit) {
    return this.http.delete(this.VISIT_URI + visit.id);
  }

}
