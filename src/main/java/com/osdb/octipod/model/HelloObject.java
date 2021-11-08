package com.osdb.octipod.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "messages")
public class HelloObject
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String message;
}
