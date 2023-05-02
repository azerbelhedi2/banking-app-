import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListComptesCourantComponent } from './list-comptes-courant.component';

describe('ListComptesCourantComponent', () => {
  let component: ListComptesCourantComponent;
  let fixture: ComponentFixture<ListComptesCourantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListComptesCourantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListComptesCourantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
