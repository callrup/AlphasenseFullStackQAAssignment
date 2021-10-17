package com.alphasense.pojoClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Pet {
	
	 private String id;
	
	    private String name;
	 
	    private Pet() {

	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	   

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    

	    @Override
	    public String toString() {
	        return "Pet{" +
	                "id='" + id + '\'' +
	                ", name='" + name + '\'' +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        Pet pet = (Pet) o;
	        return Objects.equals(this.id, pet.id) &&
	                Objects.equals(this.name, pet.name);
	    }

	    public static class Builder {
	        private String id;
	       
	        private String name;
	 

	        public Builder() {

	        }

	        public Builder withId(String id) {
	            this.id = id;
	            return this;
	        }

	       

	        public Builder withName(String name) {
	            this.name = name;
	            return this;
	        }

	       

	        public Pet build() {
	            Pet pet = new Pet();
	            pet.id = this.id;
	            pet.name = this.name;
	            return pet;
	        }


	    }

}
