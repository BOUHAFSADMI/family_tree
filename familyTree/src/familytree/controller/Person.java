/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author DarkSnow
 */
public class Person {
    private int personID;
    private String lastName;
    private String name;
    private char sexe;
    private Date birthDate;
    private String birthPlace;
    private Date deathDate;
    private Image image;
    private Person father;
    private Person mother;
    private Person partner;
    private Date weddingDate;

    public Person(int personID,String lastName,String name,char sexe,Date birthDate,String birthPlace,Date deathDate,
    Image image,Person father,Person mother,Person partner,Date weddingDate)
    {
        this.personID=personID;
        this.name=name;
        this.lastName=lastName;
        this.sexe=sexe;
        this.birthDate=birthDate;
        this.birthPlace=birthPlace;
        this.deathDate=deathDate;
        this.image=image;
        this.father=father;
        this.mother=mother;
        this.partner=partner;
        this.weddingDate=weddingDate;
    }
   
    //getters;
    public int getPersonID()
    {
        return personID;
    }
    public String getlastName()
    {
        return lastName;
    }
    public String getName()
    {
        return name;
    }
    public char getSexe()
    {
        return sexe;
    }
    public Date getBirthDate()
    {
        return birthDate;
    }
    public String getBirthPlace()
    {
        return birthPlace;
    }
     
    public Date getDeathDate()
    {
        return deathDate;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public Person getFather()
    {
        return father;
    }
    
    public Person getMother()
    {
        return mother;
    }
    
    public Person getPartner()
    {
        return partner;
    }
    public Date getWeddingDate()
    {
        return weddingDate;
    }
    
    
    //setters;
    public void setPersonID(int personID)
    {
        this.personID = personID;
    }
    public void setlastName(String lastName)
    {
        this.lastName=lastName;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setSexe(char sexe)
    {
        this.sexe=sexe;
    }
    public void setBirthDate(Date birthDate)
    {
        this.birthDate=birthDate;
    }
    public void setBirthPlace(String birthPlace)
    {
        this.birthPlace=birthPlace;
    }
     
    public void setDeathDate(Date deathDate)
    {
        this.deathDate=deathDate;
    }
    
    public void setImage(Image image)
    {
        this.image=image;
    }
    
    public void setFather(Person father)
    {
        this.father=father;
    }
    
    public void setMother(Person mother)
    {
        this.mother=mother;
    }
    
    public void setPartner(Person partner)
    {
        this.partner=partner;
    }
    public void setWeddingDate(Date weddingDate)
    {
        this.weddingDate=weddingDate;
    }
    
    public void setAll(int personID,String name,String lastName,char sexe,Date birthDate,String birthPlace,Date deathDate,
    Image image,Person father,Person mother,Person partner,Date weddingDate)
    {
        setPersonID(personID);
        setName(name);
        setlastName(lastName);
        setSexe(sexe);
        setBirthDate(birthDate);
        setBirthPlace(birthPlace);
        setDeathDate(deathDate);
        setImage(image);
        setFather(father);
        setMother(mother);
        setPartner(partner);
        setWeddingDate(weddingDate);
    }
    
    @Override
    public String toString()
    {
        return personID+" "+lastName+" "+name;
    }
}



