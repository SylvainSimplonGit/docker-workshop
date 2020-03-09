import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  // private pathRootApi = 'https://jlsgrand-restaurant-api.herokuapp.com/';
  private pathRootApi = 'http://localhost:8081/';

  constructor(
    private httpClient: HttpClient
  ) { }

  getRestaurantList() {
    const urlApi = this.pathRootApi + 'api/restaurants';
    return this.httpClient.get(urlApi);
  }

  getFilteredRestaurantList(lowest, highest) {
    let params = new HttpParams();
    params = params.append('lowestNote', lowest);
    params = params.append('highestNote', highest);

    const options = { params: params };

    const urlApi = this.pathRootApi + 'api/restaurants/filter';
    return this.httpClient.get(urlApi, options);
  }

  addReviewToRestaurant(restaurantId, review) {
    const urlApi = this.pathRootApi + 'api/restaurants/';
    return this.httpClient.post(urlApi + restaurantId + '/reviews', review);
  }

  deleteReviewFromRestaurant(restaurantId, reviewId) {
    const urlApi = this.pathRootApi + 'api/restaurants/';
    return this.httpClient.delete(urlApi + restaurantId + '/reviews/' + reviewId);
  }
}
