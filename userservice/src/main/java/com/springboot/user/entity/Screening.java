package com.springboot.user.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "screening")
public class Screening {

    private UUID id;
    private String name;
    private Movie movie;
    private Theatre theatre;
    private Date createdOn;
	private Date updatedOn;
	private UUID createdBy;
	private UUID updatedBy;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "screening_id", nullable = false)
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
	
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonManagedReference
    public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    @JsonManagedReference
    public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
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
