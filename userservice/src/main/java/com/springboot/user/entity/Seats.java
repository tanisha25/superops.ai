package com.springboot.user.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
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
@Table(name = "seats")
public class Seats {

	  private UUID id;
	  private int name;
	  private Screening screening;
	  private boolean alreadyBooked;
	  private Users user;
	  private Date createdOn;
	  private Date updatedOn;
	  private UUID createdBy;
	  private UUID updatedBy;
	  
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "seat_id", nullable = false)
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		
		@Column(name = "name", nullable = false)
		public int getName() {
			return name;
		}
		public void setName(int name) {
			this.name = name;
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
		
		@Column(name = "already_booked", nullable = false)
		public boolean isAlreadyBooked() {
			return alreadyBooked;
		}
		public void setAlreadyBooked(boolean alreadyBooked) {
			this.alreadyBooked = alreadyBooked;
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
