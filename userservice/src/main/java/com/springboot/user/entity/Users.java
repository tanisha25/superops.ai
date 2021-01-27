package com.springboot.user.entity;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2584361389667868174L;
	private UUID id;
	private String name;
	private String password;
	private Date createdOn;
	private boolean deleted;
	private Date updatedOn;
	private UUID createdBy;
	private UUID updatedBy;
	
	public Users() {
		
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
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
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="created_on",length = 35)
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
	
	@Column(name ="is_deleted")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
