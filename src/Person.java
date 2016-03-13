import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable{

	private String vorname, nachname;
	private int alter;
	
	
	public Person(String vn, String nn, int a){
		setVorname(vn);
		setNachname(nn);
		setAlter(a);
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	

	@Override
	public boolean equals(Object o){
		
		Person p = (Person)o;
		return (this.vorname.equals(p.vorname)) && (this.nachname.equals(p.nachname));// && (this.alter == p.alter);
	}
	
	@Override
	public int compareTo(Object arg0) {
		
		Person o;
		
		try{
			o = (Person)arg0;
		}catch(ClassCastException e){
			System.out.println("CompareTo-Problem");
			return 0;
		}
		
		int nn = this.nachname.compareToIgnoreCase(o.nachname);
		int vn = this.vorname.compareToIgnoreCase(o.vorname);
		
		if(nn == 0){
			if(vn == 0){
				if(this.alter == o.alter)
					return 0;
				else if(this.alter < o.alter)
					return -1;
				else if(this.alter > o.alter)
					return 1;
			}
			else if(vn > 0)
				return 1;
			
			else if(vn < 0)
				return -1;
		}
		else if(nn > 0)
			return 1;
		
		else if(nn < 0)
			return -1;
		
		return 0;
	}

	public String toString(){
		return "Vorname: "+vorname+"\tNachname: "+nachname+"\tAlter: "+alter;
	}
	
}
