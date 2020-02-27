import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantReviewListComponent } from './restaurant-review-list.component';

describe('RestaurantReviewListComponent', () => {
  let component: RestaurantReviewListComponent;
  let fixture: ComponentFixture<RestaurantReviewListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantReviewListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantReviewListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
