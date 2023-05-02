import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListComptesEpargneComponent } from './list-comptes-epargne.component';

describe('ListComptesEpargneComponent', () => {
  let component: ListComptesEpargneComponent;
  let fixture: ComponentFixture<ListComptesEpargneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListComptesEpargneComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListComptesEpargneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
