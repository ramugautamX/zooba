import { TestBed } from '@angular/core/testing';

import { ZoobaFormService } from './zooba-form.service';

describe('ZoobaFormService', () => {
  let service: ZoobaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZoobaFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
