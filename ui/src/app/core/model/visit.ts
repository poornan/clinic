import {Physician} from './physician';

export class Visit {
  id?: string;
  visitDatetime?: Date;
  physician?: Physician;
  reason?: string;
}
