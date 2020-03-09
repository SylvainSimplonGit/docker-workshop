import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import { RestaurantReviewListComponent } from './restaurant-review-list/restaurant-review-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantListComponent,
    RestaurantReviewListComponent
  ],
  imports: [
    BrowserModule, FormsModule,HttpClientModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
