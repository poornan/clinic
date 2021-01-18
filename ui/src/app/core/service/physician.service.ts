import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PhysicianService {

  PHYSICIAN_URI = environment.baseURL + 'physicians';

  constructor(
    private http: HttpClient) {
  }

  getAll() {
    return this.http.get(this.PHYSICIAN_URI);
  }

}
