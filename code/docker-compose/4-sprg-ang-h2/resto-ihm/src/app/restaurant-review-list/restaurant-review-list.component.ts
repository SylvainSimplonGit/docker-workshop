import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DataService } from '../data.service';

@Component({
  selector: 'app-restaurant-review-list',
  templateUrl: './restaurant-review-list.component.html',
  styleUrls: ['./restaurant-review-list.component.css']
})
export class RestaurantReviewListComponent implements OnInit {

  @Input() restaurantId;
  @Input() reviews;

  reviewForm = this.formBuilder.group({
    note: 3,
    comment: ''
  });

  constructor(
    private formBuilder: FormBuilder,
    private dataService: DataService
  ) { }

  ngOnInit() {
  }

  onReview(review) {
    this.dataService.addReviewToRestaurant(this.restaurantId, review).subscribe(savedReview => console.log(savedReview));
  }

  onRemoveReview(reviewId) {
    this.dataService.deleteReviewFromRestaurant(this.restaurantId, reviewId).subscribe();
  }

}
