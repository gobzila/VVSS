package agenda.model.base;

import agenda.exceptions.InvalidFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
	private String Name;
	private String Address;
	private String Telefon;
	
	public Contact(){
		Name = "";
		Address = "";
		Telefon = "";
	}
	
	public Contact(String name, String address, String telefon) throws InvalidFormatException{
		if (!validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		if (!validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		if (!validAddress(address)) throw new InvalidFormatException("Cannot convert", "Invalid address");
		Name = name;
		Address = address;
		Telefon = telefon;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) throws InvalidFormatException {
		if (!validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) throws InvalidFormatException {
		if (!validAddress(address)) throw new InvalidFormatException("Cannot convert", "Invalid address");
		Address = address;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) throws InvalidFormatException {
		if (!validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		Telefon = telefon;
	}

	public static Contact fromString(String str, String delim) throws InvalidFormatException
	{
		String[] s = str.split(delim);
		if (s.length!=4) throw new InvalidFormatException("Cannot convert", "Invalid data");
		if (!validTelefon(s[2])) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		if (!validName(s[0])) throw new InvalidFormatException("Cannot convert", "Invalid name");
		if (!validAddress(s[1])) throw new InvalidFormatException("Cannot convert", "Invalid address");
		
		return new Contact(s[0], s[1], s[2]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Name);
		sb.append("#");
		sb.append(Address);
		sb.append("#");
		sb.append(Telefon);
		sb.append("#");
		return sb.toString();
	}
	
	private static boolean validName(String str)
	{
		if (str.length()<=2) return false;
		return str.matches("^[A-Za-z]*");
	}
	
	private static boolean validAddress(String str)
	{
		return true;
	}
	
	private static boolean validTelefon(String tel)
	{
		if (tel.length()!=10) return false;
		return tel.matches("[0-9]*?");
	}
	
		
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Contact)) return false;
		Contact o = (Contact)obj;
		if (Name.equals(o.Name) && Address.equals(o.Address) &&
				Telefon.equals(o.Telefon))
			return true;
		return false;
	}
	
}
