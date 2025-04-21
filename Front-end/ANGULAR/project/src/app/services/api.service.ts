import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  urlBase = environment.url + 'api';
   
  public getAll(entidad: string): Observable<any> {
    return this.http.get<any>(`${this.urlBase}/${entidad}/getAll`);
  }
  public save(entidad:string, data: any) {
    return this.http.post(`${this.urlBase}/${entidad}/`,data);
  }
  public update(entidad:string,id: number, data: any) {
    return this.http.put(`${this.urlBase}/${entidad}/update/${id}`,data);
  }
  public delete(entidad: string, id: number) {
    return this.http.delete(`${this.urlBase}/${entidad}/delete/${id}`);
  }
  public filter(entidad: string, filtros: { [key: string]: any }): Observable<any> {
    let params = new HttpParams();
    for (const key in filtros) {
      if (filtros[key] !== null && filtros[key] !== '') {
        params = params.set(key, filtros[key]);
      }
    }
  
    return this.http.get(`${this.urlBase}/${entidad}/filter`, { params });
  }
  
}
