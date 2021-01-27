package com.springboot.user.entity;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "theatre")
public class Theatre {
	private UUID id;
	private String name;
	private Date createdOn;
	private Date updatedOn;
	private UUID createdBy;
	private UUID updatedBy;
	private List<Screening> screenings;
	@Access(AccessType.PROPERTY)
    @ElementCollection(targetClass=Screening.class)
	@OneToMany(targetEntity=Screening.class, mappedBy="theatre", cascade= CascadeType.ALL, fetch=FetchType.EAGER)	
	public List<Screening> getScreenings() {
		return screenings;
	}
	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "theatre_id", nullable = false)
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

  