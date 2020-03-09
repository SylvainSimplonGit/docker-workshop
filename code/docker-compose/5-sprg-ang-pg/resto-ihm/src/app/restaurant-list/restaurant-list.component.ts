import {Component, OnInit} from '@angular/core';
import {DataService} from '../data.service';

import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  restaurantList;

  filterForm = this.formBuilder.group({
    lowestNote: 0,
    highestNote: 5
  });

  constructor(
    private dataService: DataService,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.restaurantList = this.dataService.getRestaurantList();
  }

  onFilter(filteringValues) {
    this.restaurantList = this.dataService.getFilteredRestaurantList(filteringValues.lowestNote, filteringValues.highestNote);
  }

}
