package com.springboot.user.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "seatBooking")
public class SeatBooking {

	  private UUID id;
	  private Users user;
	  private Screening screening;
	  private boolean deleted;
	  private List<Integer> seatsRequested;
	  private String status;
	  private Date createdOn;
	  private Date updatedOn;
	  private UUID createdBy;
	  private UUID updatedBy;
	  
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "seat_booking_id", nullable = false)
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		
	    @ManyToOne
	    @JoinColumn(name = "screening_id")
	    @JsonManagedReference
	    public Screening getScreening() {
			return screening;
		}
		public void setScreening(Screening screening) {
			this.screening = screening;
		}
		
		@OneToOne
	    @JoinColumn(name = "user_id")
	    @JsonManagedReference
	    public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		
		@Column(name = "seats_requested", nullable = false)
	    @ElementCollection(targetClass=Integer.class)
		public List<Integer> getSeatsRequested() {
			return seatsRequested;
		}
		public void setSeatsRequested(List<Integer> seatsRequested) {
			this.seatsRequested = seatsRequested;
		}
		@Column(name = "deleted", nullable= false)
		public boolean isDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		@Column(name = "status", nullable = false)
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name ="created_on", nullable= false ,length = 35)
		public Date getCreatedOn() {
			return createdOn;
		}
		
		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name ="updated_on" ,length = 35)
		public Date getUpdatedOn() {
			return updatedOn;
		}
		public void setUpdatedOn(Date updatedOn) {
			this.updatedOn = updatedOn;
		}
		
		@Column(name ="created_by" ,length = 35)
		public UUID getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(UUID createdBy) {
			this.createdBy = createdBy;
		}
		
		@Column(name ="updated_by" ,length = 35)
		public UUID getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(UUID updatedBy) {
			this.updatedBy = updatedBy;
		}
		@PrePersist
		public void prePersist()
		{
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			createdOn = currentTimestamp;
			updatedOn = currentTimestamp;
			
			if(id == null)
			{
				id = UUID.randomUUID();
			}
		}
		
		@PreUpdate
		public void preUpdate()
		{
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			updatedOn = currentTimestamp;
		}
		
	    
}
