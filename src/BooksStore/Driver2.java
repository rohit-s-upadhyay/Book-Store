package BooksStore;

import java.util.InputMismatchException;

import java.util.LinkedHashMap;
//import java.util.Iterator;
import java.util.LinkedList;

import java.util.Scanner;
import java.util.Set;
//import java.util.TreeMap;
import java.util.regex.Pattern;

interface Registration {
	void login();

	void signUp();
}

class Register implements Registration {
	private String name; // hiding data

	private long mobileNo;// = 8007423415L;

	private String email;

	String address;

	private String password ;//= "admin";

	public String getName() {
		return name;
	}

	public void setName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Name");
		name = sc.nextLine();

		if (name == null || name.isEmpty()) {
			System.out.println("Please Enter Something");
			setName();
		}
		String regexName = "\\p{Upper}(\\p{Lower}+\\s?)";
		Pattern pattern = Pattern.compile(regexName);
		if (pattern.matcher(name).matches()) {
			this.name = name;
			System.out.println("Name is valid");
			
		} else {
			System.err.println("Only Alphabets Are Allowed (-_-)");
			System.err.println("!!---- First Letter Should Be Capital---- ");
			System.err.println("Please Enter valid Name ");
			setName();
		}
		
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter your contact no");
			mobileNo = sc.nextLong();
			if (mobileNo > 999999999L && mobileNo < 100000000000L) {
				System.out.println("Contact Number is Valid");
				this.mobileNo = mobileNo;
			} else {
				System.err.println("Please enter 10 digits");
				setMobileNo(); // recursion
			}
		} catch (InputMismatchException e) {

			System.err.println("You Can Not Enter Character Inside Contact No !");
			System.err.println("Please Try Again  (-_-)");
			setMobileNo();
			
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail() {
		// this.email = email;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email Address");
		email = sc.nextLine();
		if (email == null || email.isEmpty()) {
			System.out.println("Please Enter Something");
			setEmail();
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Pattern pattern = Pattern.compile(regex);
		if (pattern.matcher(email).matches()) {
			System.out.println("Email Id is valid");

		} else {
			System.err.println("Email Id Is Invalid (-_-)");
			System.err.println("Please enter valid Email Id");
			setEmail();
		}
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {//method overriding
		System.out.println("*-------Login Process-------*");

		Scanner sc = new Scanner(System.in);
		try {
			if (this.mobileNo == 0L) {
				throw new WithoutRegistrationLoginException("You can Not Login Without Registration");
			} else {
				System.out.println("Enter the Contact No as a Username");
				long mobileNo = sc.nextLong();
				sc.nextLine();
				System.out.println("Enter the password");
				String password = sc.nextLine();
				if (this.mobileNo == mobileNo && this.password.equals(password)) {
					System.out.println("Login In Successfully");
				} else {
					System.err.println("Please Try Again");
					login();
				}
			}
		} catch (WithoutRegistrationLoginException e) {
			System.err.println("You can Not Login Without Registration");
			System.err.println("First You have to do Registration..");
			signUp();
		}
		Books b = new Books();
		b.booksInfo();
	}

	public void signUp() {
		System.out.println("*------------Registration Process----------*");
		System.out.println(" ");
		System.out.println(" (^_^)  Please Enter Valid Information  (^_^)");
		Scanner sc = new Scanner(System.in);
		setName();
		setMobileNo(); // validation
		setEmail();
		System.out.println("Enter your Address");
		address = sc.nextLine();
		System.out.println("Enter your Password");
		password = sc.nextLine();
		System.out.println("SignUp Successfully");
		login();
		sc.close();
	}
}

class Books extends Register {
	static LinkedList<String> l = new LinkedList<String>();

	static double bill;

	public void totalBill() {
		System.out.println("Your total bill amount is " + bill);
		int discount = (int) (bill * 0.1);
		bill = bill - discount;
		System.out.println("After discount the bill is " + bill);
		System.out.println("The Books ordered are :");
		
		for (Object obj : l) {
			System.out.println(obj);
		}
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Press 1 for UPI");
			System.out.println("Press 2 for cash");
			Books b = new Books();

			int n = sc.nextInt();
			switch (n) {
			case 1:
				setMobileNo();
				payment(this.getMobileNo());
				System.out.println("Thank You For Purchase Book's ");
				System.out.println("Visit Again And Again");
				break;
			case 2:
				payment(bill);
				System.out.println("Thank You For Purchase Book's I");
				System.out.println("Visit Again");
				break;
			default:
				bill = bill + discount;
				totalBill();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			totalBill();
		}

		System.exit(0);
	}

//	@Override
//	public String toString() {
//		// System.out.println(id);
//		System.out.println(getName());
//		return "";
//	}

	public void payment(long upi) {
		System.out.println(" Your Amount is " + bill);
		System.out.println("Thank you for paying " + bill);
	}

	public void payment(double cash) {
		System.out.println("Thank you for paying " + cash);
	}

	public void booksInfo() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please select which type of book you want (^.^)");
		System.out.println("Press 1 for Fiction");
		System.out.println("Press 0 to exit");

		try {
			int n = sc.nextInt();

			switch (n) {
			case 1:
				System.out.println("Fiction");
				Fiction f = new Fiction();
				f.fiction();
				break;
			case 0: // exit
				System.out.println("exit");
				System.exit(0);
				break;
			default:
				System.err.println("Please chooose the correct option");
				booksInfo();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			booksInfo();
		}
	}
}

class Fiction extends Books {
	public void fiction() {
		System.out.println("!!!...........FICTION...........!!!");
		System.out.println(" ");
		System.out.println("Press 1 for Science fiction");
		System.out.println("Press 2 for Mystery");
		System.out.println("Press 3 for Historical fiction");
		System.out.println("Press 4 for Romance");
		System.out.println("Press 5 for Classic");
		System.out.println("Press 6 for Horror");
		System.out.println("Press 7 for Go Back ");
		System.out.println("Press 0 for Exit ");

		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1: // Science fiction
				System.out.println("Science fiction");
				Sciencefiction s = new Sciencefiction();
				s.scienceFiction();
				break;
			case 2: // Mystery
				System.out.println("Mystery");
				Mysteryfiction m = new Mysteryfiction();
				m.mysteryFiction();
				break;
			case 3:
				System.out.println("Historical fiction");
				Historicalfiction h = new Historicalfiction();
				h.historicalFiction();
				break;
			case 4:
				System.out.println("Romance");
				Romancefiction t = new Romancefiction();
				t.romanceFiction();
				break;/*
						 * 1. Science fiction 2. Mystery 3. Historical fiction 4. Thriller 5. Young
						 * adult 6. Realistic fiction 7. Crime 8.Classic 9.Horror 10.Romance
						 */
			case 5:
				System.out.println("Realistic fiction");
				Classicfiction r = new Classicfiction();
				r.classicFiction();
				break;
			case 6:
				System.out.println("Crime");
				Horrorfiction c = new Horrorfiction();
				c.horrorFiction();
				break;
			case 7:
				booksInfo();
				break;
			case 0: // exit
				System.out.println("Thank You " + getName() + " Visit Again..(*_*)");
				System.exit(0);
				break;
			default:
				System.out.println("Please chooose the correct option");
				fiction();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			fiction();
		}

	}
}

class Sciencefiction extends Fiction {
	public void scienceFiction() {
		System.out.println("!!!...........SCIENCE FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				scienceFictionMarathi();
				break;
			case 2:
				scienceFictionEnglish();
				break;
			case 3:
				scienceFictionHindi();
				break;
			case 4:
				fiction();
				break;
			default:
				System.err.println("Please try again");
				scienceFiction();
				break;
			}
			sc.close();
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			scienceFiction();
		}
	}

	public void scienceFictionMarathi() {

		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==  ==|");
		System.out.println("|                                                                         |");
		System.out.println("|           ...(*_*)    Mararthi Science Fiction Books  (*_*)...          |");
		System.out.println("|                                                                         |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Georges Secret Key to the Universe  ||  (Marathi, Paperback, HAWKING LUCY)");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Pruthvivar Manus Uprach!  ||  (Marathi, Paperback, NADKARNI SURESHCHANDRA)");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Vidnyanatil Saras Ani Suras  ||  (Marathi, Paperback, GOKHALE RAHUL)");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Drushtibhram   ||  (Marathi, Paperback, PHONDKE BAL)");
		System.out.println(" ");
		System.out.println("Press 5 for ||  State of Fear  ||  (Marathi, Paperback, CRICHTON MICHAEL)");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Georges Secret Key to the Universe * (Marathi, Paperback, HAWKING LUCY)");
				System.out.println(" ");
				System.out.println("      Author name  Pramod Jogalekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println("--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Georges Secret Key to the Universe");
				}  else {
					scienceFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(	"  You have selected :~  * Pruthvivar Manus Uprach!  * (Marathi, Paperback, NADKARNI SURESHCHANDRA)");
				System.out.println(" ");
				System.out.println("      Author name  Sureshchandr Nadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹110");
				System.out.println("|");
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 110;
					l.add("Pruthvivar Manus Uprach!");
				} else {
					scienceFictionMarathi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(
						"      You have selected :~  * Vidnyanatil Saras Ani Suras * (Marathi, Paperback, GOKHALE RAHUL)");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Gokhale");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("Vidnyanatil Saras Ani Suras");
				} else {
					scienceFictionMarathi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Drushtibhram  *(Marathi, Paperback, PHONDKE BAL)");
				System.out.println(" ");
				System.out.println("      Author name  Dr.Bal Falake");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹250");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				
				System.out.println("Press 2 For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 250;
					l.add("Drushtibhram");
				}
				else {
					scienceFictionMarathi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * State of Fear * (Marathi, Paperback, CRICHTON MICHAEL)");
				System.out.println(" ");
				System.out.println("      Author name  Pramod Jogalekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("State of Fear ");
				} else {
					scienceFictionMarathi();
				}
				break;
			case 6:
				scienceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				scienceFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
			sc.close();
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			scienceFictionMarathi();
		}

	}

	public void scienceFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == =|");
		System.out.println("|                                                                        |");
		System.out.println("|              ...(*_*)   English Science Fiction Books  (*_*)...        |");
		System.out.println("|                                                                        |");
		System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Greatest Works of H G Wells Deluxe Hardbound Edition Hardcover – 1 January 2022");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Best American Science Fiction And Fantasy 2021: A Christmas Holiday Book for Kids");
		System.out.println(" ");
		System.out.println("Press 3 for ||  The Hidden Hindu");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Sinbad and the Rise of Iblis Paperback – 14 August 2023");
		System.out.println(" ");
		System.out.println("Press 5 for ||  The Lost World Paperback – 1 May 2021");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(
						"      You have selected :~  * Greatest Works  * of H G Wells Deluxe Hardbound Edition Hardcover – 1 January 2022");
				System.out.println(" ");
				System.out.println("      Author name  H.G.Well");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press any For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Greatest Works");
				} else {
					scienceFictionEnglish();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Best American Science Fiction *  Fantasy 2021 A Christmas Holiday Book for Kids");
				System.out.println(" ");
				System.out.println("      Author name   John Joseph Adams");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹1210");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press any no For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 1210;
					l.add("Best American Science Fiction");
				}
				else {
					scienceFictionEnglish();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Hidden Hindu *");
				System.out.println(" ");
				System.out.println("      Author name  Akshat Gupta");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press any no For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("The Hidden Hindu");
				}
				else {
					scienceFictionEnglish();
				}
				break;
			case 4:
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(	"      You have selected :~  * Sinbad and the Rise of Iblis  * Paperback – 14 August 2023");
				System.out.println(" ");
				System.out.println("      Author name   Kevin Missal ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press any no For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("Sinbad and the Rise of Iblis");
				}
				else {
					scienceFictionEnglish();
				}
				break;
			case 5:
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Lost World  * Paperback – 1 May 2021");
				System.out.println(" ");
				System.out.println("      Author name   Conan Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹180");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press Any For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 180;
					l.add("The Lost World");
				} else {
					scienceFictionEnglish();
				}
				break;
			case 6:
				scienceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				scienceFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
			sc.close();
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			scienceFictionEnglish();
		}
	}

	public void scienceFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("|                                                                       |");
		System.out.println("|             ...(*_*)  Hindi Science Fiction Books (*_*)...            |");
		System.out.println("|                                                                       |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("");
		System.out.println(	"Press 1 for ||  Bharatiya Sena Ke Shoorveeron Ki Shauryagathayen  (Hindi, Paperback, Aroor Shiv)");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Samay Ka Saral Aur Sanshipt Itihas (Hindi Edition of A Briefer History Of Time) ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  K-The Last Warrior(Hindi)");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Prem Aur Sazish  (Hindi, Paperback, Paul Sanjay Kumar)");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Antariksha Mein Jeevan  (Hindi, Hardcover, Shankar Kali)");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(	"      You have selected :~  * Bharatiya Sena Ke Shoorveeron Ki Shauryagathayen   * (Hindi, Paperback, Aroor Shiv)");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Sinh");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
	     		System.out.println(" ");
		
				System.out.println("  Press any no For Chose Another book if you don't like ");
				System.out.println("--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
					l.add("Bharatiya Sena Ke Shoorveeron Ki Shauryagathayen");
				}
				else {
					scienceFictionHindi();
				}
				break;
			case 2:
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Samay Ka Saral Aur Sanshipt Itihas  * (Hindi Edition of A Briefer History Of Time) ");
				System.out.println(" ");
				System.out.println("      Author name  Sureshchandr Nadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹235");
				System.out.println("|");
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 235;
					l.add("Samay Ka Saral Aur Sanshipt Itihas");
				}
				else {
					scienceFictionHindi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * K-The Last Warrior  *(Hindi)");
				System.out.println(" ");
				System.out.println("      Author name  Tarun jaulii");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹290");
				System.out.println("|");
				System.out.println(						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 290;
					l.add("K-The Last Warrior");
				}				
				else {
					scienceFictionHindi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(
						"      You have selected :~  * Prem Aur Sazish   * (Hindi, Paperback, Paul Sanjay Kumar)");
				System.out.println(" ");
				System.out.println("      Author name  Sanjay Kumar paal");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹275");
				System.out.println("|");
				System.out.println(						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 275;
					l.add("Prem Aur Sazish");
				}
				else {
					scienceFictionHindi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(						"      You have selected :~  * Antariksha Mein Jeevan  * (Hindi, Hardcover, Shankar Kali)");
				System.out.println(" ");
				System.out.println("      Author name  Kalli Shankar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹420");
				System.out.println("|");
				System.out.println(						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 420;
					l.add("Antariksha Mein Jeevan");
				}  else {
					scienceFictionHindi();
				}
				break;
			case 6:
				scienceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				scienceFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			scienceFictionHindi();
		}

	}
}

class Mysteryfiction extends Fiction {
	public void mysteryFiction() {
		System.out.println("!!!...........MYSTERY FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				mysteryFictionMarathi();
				break;
			case 2:
				mysteryFictionEnglish();
				break;
			case 3:
				mysteryFictionHindi();
				break;
			case 4:
				fiction();
				break;
			default:
				System.err.println("Invalid input..!  Press Given Choice");
				mysteryFiction();
				break;
			}
			sc.close();
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			mysteryFiction();
		}
	}

	public void mysteryFictionMarathi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("|                                                                                   |");
		System.out.println("|           ...(*_*)    Marathi Crime, Mystery And Thriller Books   (*_*)...        |");
		System.out.println("|                                                                                   |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("");
		System.out.println("Press 1 for ||  ChandraGudh  (Marathi Edition) ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Mahamaya Nilavanti   [perfect] Sumedh [Jan 01, 2023]");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Samarpan  ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  The Runaway Jury  ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Gone Girl ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * ChandraGudh   *(Marathi Edition) ");
				System.out.println(" ");
				System.out.println("      Author name   Sudhanwa Panse ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹120");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 120;
					l.add("ChandraGudh");
				}
				else {
					mysteryFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out
						.println("      You have selected :~  * Mahamaya Nilavanti   *[perfect] Sumedh [Jan 01, 2023]");
				System.out.println(" ");
				System.out.println("      Author name   Sumedh ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("Mahamaya Nilavanti ");
				}
				else {
					mysteryFictionMarathi();
				}
				break;
			case 3:
				System.out.println(	"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Samarpan  *");
				System.out.println(" ");
				System.out.println("      Author name  Mahendra Pethkar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹337");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(	"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 337;
					l.add("Samarpan");
				}
				else {
					mysteryFictionMarathi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Runaway Jury  *");
				System.out.println(" ");
				System.out.println("      Author name  GRISHAM JOHN");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹350");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 350;
					l.add("The Runaway Jury");
				}
				else {
					mysteryFictionMarathi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Gone Girl  * ");
				System.out.println(" ");
				System.out.println("      Author name  Sai Sane");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹675");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 675;
					l.add("Gone Girl");
				}
				else {
					mysteryFictionMarathi();
				}
				break;
			case 6:
				mysteryFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				mysteryFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
			sc.close();
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			mysteryFictionMarathi();
		}

	}

	public void mysteryFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==|");
		System.out.println("|                                                                                 |");
		System.out.println("|         ...(*_*)   English Crime, Mystery And Thriller Books   (*_*)...         |");
		System.out.println("|                                                                                 |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  THE SILENT PATIENT  ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Girl, Alone");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Three Sisters");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Mission Kashmir: An Intelligence Operation in the valley ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  War of Lanka ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * THE SILENT PATIENT  * ");
				System.out.println(" ");
				System.out.println("      Author name  Alex Michaelides ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹205");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 205;
					l.add("THE SILENT PATIENT");
				}
				else {
					mysteryFictionEnglish();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Girl, Alone * ");
				System.out.println(" ");
				System.out.println("      Author name    Blake Pierce");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹794");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 794;
					l.add("Girl, Alone ");
				}
				else {
					mysteryFictionEnglish();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Three Sisters *");
				System.out.println(" ");
				System.out.println("      Author name   O. J. Mullen");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹160");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 160;
					l.add("Three Sisters");
				}
				else {
					mysteryFictionEnglish();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(
						"      You have selected :~  * Mission Kashmir: An Intelligence Operation in the valley  *");
				System.out.println(" ");
				System.out.println("      Author name    Udayaditya Mukherjee ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹207");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");

				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 207;
					l.add("Mission Kashmir: An Intelligence Operation in the valley");
				}
				else {
					mysteryFictionEnglish();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * War of Lanka   * ");
				System.out.println(" ");
				System.out.println("      Author name   Conan Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹324");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 324;
					l.add("War of Lanka");
				}
				else {
					mysteryFictionEnglish();
				}
				break;
			case 6:
				mysteryFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				mysteryFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			mysteryFictionEnglish();
		}
		sc.close();
	}

	public void mysteryFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                                      |");
		System.out.println("|          ....(*_*)   Hindi Crime, Mystery And Thriller Books  (*_*)                  |");
		System.out.println("|                                                                                      |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println(" ");
		System.out.println("Press 1 for ||  Kashi - Kaale Mandir ka Rahasya ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  God is a Gamer ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Dr. Ambedkar Aarthik Vichar Avam Darshan - Book by Dr. BR Ambedkar ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Black Warrant ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Ek Karod Ka Joota ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Kashi - Kaale Mandir ka Rahasya   *");
				System.out.println(" ");
				System.out.println("      Author name  VINEET BAJPAI");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹249");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 249;
					l.add("Kashi - Kaale Mandir ka Rahasya");
				}
//			else if(n==2) {
//		    }
				else {
					mysteryFictionHindi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * God is a Gamer  * ");
				System.out.println(" ");
				System.out.println("      Author name  Subramanian Ravi");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹450");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 450;
					l.add("God is a Gamer");
				}
//			else if(n==2) {
//			}
				else {
					mysteryFictionHindi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println(
						"      You have selected :~  * Dr. Ambedkar Aarthik Vichar Avam Darshan - Book by Dr. BR Ambedkar  *");
				System.out.println(" ");
				System.out.println("      Author name  Jadhav Narendra");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹580");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 580;
					l.add("Dr. Ambedkar Aarthik Vichar Avam Darshan");
				}
//				else if(n==2) {
//				}
				else {
					mysteryFictionHindi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Black Warrant  *");
				System.out.println(" ");
				System.out.println("      Author Name  Sunil Gupta");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹267");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 267;
					l.add("Black Warrant");
				}
//				else if(n==2) {
//				}
				else {
					mysteryFictionHindi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Ek Karod Ka Joota  *");
				System.out.println(" ");
				System.out.println("      Author Name  Pathak Surendra Mohan");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹214");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 214;
					l.add("Ek Karod Ka Joota");
				}
//				else if(n==2) {
//				}
				else {
					mysteryFictionHindi();
				}
				break;
			case 6:
				mysteryFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				mysteryFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			mysteryFictionHindi();
		}
	}

}

class Historicalfiction extends Fiction {
	public void historicalFiction() {
		System.out.println("!!!...........HISTORICAL FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				historicalFictionMarathi();
				break;
			case 2:
				historicalFictionEnglish();
				break;
			case 3:
				historicalFictionHindi();
				break;
			case 4:
				Fiction f = new Fiction();
				f.fiction();
				break;
			default:
				System.err.println("Please try again");
				historicalFiction();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			historicalFiction();
		}
		sc.close();
	}

	public void historicalFictionMarathi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  |");
		System.out.println("|                                                                            |");
		System.out.println("|          ...(*_*)    Mararthi Historical Fiction Books    (*_*)...         |");
		System.out.println("|                                                                            |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == == ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Yayati ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Shriman Yogi ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Radheya ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Gupther Bahirji Naik ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Shivbharat ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Yayati  *");
				System.out.println(" ");
				System.out.println("      Author name  V.S.Khadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Yayati");
				}
//			else if(n==2) {	
//			}
				else {
					historicalFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Shriman Yogi  *");
				System.out.println(" ");
				System.out.println("      Author name   Ranjeet Desai");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹210");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 210;
					l.add("Shriman Yogi");
				}
//			else if(n==2) {
//			}
				else {

					historicalFictionMarathi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Radheya  *");
				System.out.println(" ");
				System.out.println("      Author name  Ranjeet Desai");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹599");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 599;
					l.add("Radheya");
				}
//				else if(n==2) {
//				}
				else {
					historicalFictionMarathi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Gupther Bahirji Naik  *");
				System.out.println(" ");
				System.out.println("      Author name  Meenakshi vaid");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹389");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 389;
					l.add("Gupther Bahirji Naik ");
				}
//				else if(n==2) {
//				}
				else {
					historicalFictionMarathi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Shivbharat  *");
				System.out.println(" ");
				System.out.println("      Author name  Kavindra Parmanand");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹249");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 249;
					l.add("Shivbharat");
				}
//				else if(n==2) {
//				}
				else {
					historicalFictionMarathi();
				}
				break;
			case 6:
				historicalFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				historicalFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			historicalFictionMarathi();
		}
		sc.close();
	}

	public void historicalFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  |");
		System.out.println("|                                                                            |");
		System.out.println("|          ...(*_*)   English Historical Fiction Books  (*_*)...             |");
		System.out.println("|                                                                            |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  |");
		System.out.println("");
		System.out.println("Press 1 for ||  The Other Boleyn Girl ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  The White Queen ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Wolf Hall ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Innocent Traitor (Hardcover) ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  The Constant Princess ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Other Boleyn Girl ");
				System.out.println(" ");
				System.out.println("      Author name   Philippa Gregory ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹570");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 570;
					l.add("The Other Boleyn Girl");
				}
//			else if(n==2) {
//			}
				else {
					historicalFictionEnglish();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The White Queen  ");
				System.out.println(" ");
				System.out.println("      Author name   John Joseph Adams");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹210");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 210;
					l.add("The White Queen");
				}
//			else if(n==2) {	
//			}
				else {
					historicalFictionEnglish();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Wolf Hall  *");
				System.out.println(" ");
				System.out.println("      Author name   Hilary Mantel");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹110");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 110;
					l.add("Wolf Hall");
				}
//				else if(n==2) {
//				}
				else {
					historicalFictionEnglish();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Innocent Traitor (Hardcover)  *");
				System.out.println(" ");
				System.out.println("      Author name   Kevin Missal ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
					l.add("Innocent Traitor");
				}
//				else if(n==2) {	
//				}
				else {
					historicalFictionEnglish();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Constant Princess *");
				System.out.println(" ");
				System.out.println("      Author name   Conan Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹180");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 180;
					l.add("The Constant Princess");
				}
//				else if(n==2) {			
//				}
				else {
					historicalFictionEnglish();
				}
				break;
			case 6:
				historicalFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				historicalFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			historicalFictionEnglish();
		}
		sc.close();
	}

	public void historicalFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                              |");
		System.out.println("|              ...(*_*)   Hindi Historical Fiction Books  (*_*)...             |");
		System.out.println("|                                                                              |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Somnath ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Patna Blues  ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Harappa");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Sita - Mithila Ki Yoddha ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Samarsiddha ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Somnath  *");
				System.out.println(" ");
				System.out.println("      Author name  Chatursen Acharya");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹370");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 370;
					l.add("Somnath");
				}
//			else if(n==2) {
//          }
				else {
					historicalFictionHindi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Patna Blues  * ");
				System.out.println(" ");
				System.out.println("      Author name  Khan Abdullah");
				System.out.println(" ");
				System.out.println("      the price of  Book is  ₹435");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 435;
					l.add("Patna Blues");
				}
//			else if(n==2) {	
//			}
				else {
					historicalFictionHindi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Harappa  *");
				System.out.println(" ");
				System.out.println("      Author name  Vinit jaulii");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹290");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 290;
					l.add("Harappa");
				}
//				else if(n==2) {
//				}
				else {
					historicalFictionHindi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Sita - Mithila Ki Yoddha  *");
				System.out.println(" ");
				System.out.println("      Author name  Sanjay Kumar paal");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹275");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 275;
					l.add("Sita - Mithila Ki Yoddha");
				} else {
					historicalFictionHindi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Samarsiddha *");
				System.out.println(" ");
				System.out.println("      Author name  Sandip naiyar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹420");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 420;
					l.add("Samarsiddha");
				} 
				else {
					historicalFictionHindi();
				}
				break;
			case 6:
				historicalFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				historicalFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			historicalFictionHindi();
		}
		sc.close();
	}

}

class Romancefiction extends Fiction {
	public void romanceFiction() {
		System.out.println("!!!...........ROMANCE FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				romanceFictionMarathi();
				break;
			case 2:
				romanceFictionEnglish();
				break;
			case 3:
				romanceFictionHindi();
				break;
			case 4:
				Fiction f = new Fiction();
				f.fiction();
				break;
			default:
				System.err.println("Please try again");
				romanceFiction();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			romanceFiction();
		}
		sc.close();
	}

	public void romanceFictionMarathi() {
		System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                             |");
		System.out.println("             ...(*_*)    Marathi Romance Fiction Books  (*_*)...              |");
		System.out.println("|                                                                             |");
		System.out.println("==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Duniya Premachi ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Te Pakke Premveer ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  The Price of Love ");
		System.out.println(" ");
		System.out.println("Press 4 for || Swapnapoorti ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Half Girlfriend ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		System.out.println("      Press 3 For Chose Another book if you don't like ");
		System.out.println(
				"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Duniya Premachi *");
				System.out.println(" ");
				System.out.println("      Author name  Nisha Jogalekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
					l.add("Duniya Premachi");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Te Pakke Premveer *");
				System.out.println(" ");
				System.out.println("      Author name  Sivaram mahajan");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹310");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 310;
					l.add("Te Pakke Premveer");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionMarathi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *   The Price of Love  *");
				System.out.println(" ");
				System.out.println("      Author name  James Gokhale");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("he Price of Love");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionMarathi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Swapnapoorti  *");
				System.out.println(" ");
				System.out.println("      Author name  Sangitaa Falake");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹250");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 250;
					l.add("Swapnapoorti");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionMarathi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Half Girlfriend  *");
				System.out.println(" ");
				System.out.println("      Author name  Pravin Jogalekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("Half Girlfriend");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionMarathi();
				}
				break;
			case 6:
				romanceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				romanceFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			romanceFictionMarathi();
		}
		sc.close();

	}

	public void romanceFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                          |");
		System.out.println("|            ....(*_*)    English Romance Fiction Books  (*_*)...          |");
		System.out.println("|                                                                          |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  The Girl He Never Noticed ");
		System.out.println(" ");
		System.out.println("Press 2 for || My Bad Boy");
		System.out.println(" ");
		System.out.println("Press 3 for ||  ALMOST A BRIDE");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Dumped!");
		System.out.println(" ");
		System.out.println("Press 5 for ||  The Replacement Girlfriend");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Girl He Never Noticed ");
				System.out.println(" ");
				System.out.println("      Author name  H.G.Well");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("The Girl He Never Noticed ");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionEnglish();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  My Bad Boy  *");
				System.out.println(" ");
				System.out.println("      Author name   John Joseph Adams");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹210");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 210;
					l.add("My Bad Boy");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionEnglish();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * ALMOST A BRIDE *");
				System.out.println(" ");
				System.out.println("      Author name  Akshat Gupta");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("ALMOST A BRIDE");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionEnglish();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Dumped! *");
				System.out.println(" ");
				System.out.println("      Author name   Kevin Missal ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("Dumped!");
				}
//				else if(n==2) {	
//				}
				else {
					romanceFictionEnglish();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * The Replacement Girlfriend  *");
				System.out.println(" ");
				System.out.println("      Author name   Conan Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹180");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 180;
					l.add("The Replacement Girlfriend ");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionEnglish();
				}
				break;
			case 6:
				romanceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				romanceFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			romanceFictionEnglish();
		}
		sc.close();
	}

	public void romanceFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                      |");
		System.out.println("|             ...(*_*)    Hindi Romance Fiction Books  (*_*)...        |");
		System.out.println("|                                                                      |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println(" ");
		System.out.println("Press 1 for ||  Alankar ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Ek Adhuri Kahani ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Devdas");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Musafir Cafe");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Antariksha Mein Jeevan  (Hindi, Hardcover, Shankar Kali)");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		try {
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Alankar  *");
				System.out.println(" ");
				System.out.println("      Author name  Premchandra Sinh");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
					l.add("Alankar");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionHindi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Ek Adhuri Kahani ");
				System.out.println(" ");
				System.out.println("      Author name  Shubham Sharma ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹235");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 235;
					l.add("Ek Adhuri Kahani");
				}
//			else if(n==2) {
//			}
				else {
					romanceFictionHindi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Devdas  *");
				System.out.println(" ");
				System.out.println("      Author name  Tarun jaulii");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹290");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 290;
					l.add("Devdas");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionHindi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Musafir Cafe  *");
				System.out.println(" ");
				System.out.println("      Author name  Sanjay Kumar paal");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹275");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 275;
					l.add("Musafir Cafe");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionHindi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Kulfi and Cappuccino  *");
				System.out.println(" ");
				System.out.println("      Author name  Kalli Shankar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹420");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 420;
					l.add("Kulfi and Cappuccino ");
				}
//				else if(n==2) {
//				}
				else {
					romanceFictionHindi();
				}
				break;
			case 6:
				romanceFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				romanceFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			romanceFictionHindi();
		}
	}

}

class Classicfiction extends Fiction {
	public void classicFiction() {
		System.out.println("!!!...........CLASSIC  FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				classicFictionMarathi();
				break;
			case 2:
				classicFictionEnglish();
				break;
			case 3:
				classicFictionHindi();
				break;
			case 4:
				Fiction f = new Fiction();
				f.fiction();
				break;
			default:
				System.err.println("Please try again");
				classicFiction();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			classicFiction();
		}
		sc.close();
	}

	public void classicFictionMarathi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("|                                                                           |");
		System.out.println("|             ...(*_*)    Marathi Classic Fiction  Books  (*_*)...          |");
		System.out.println("|                                                                           |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println(" ");
		System.out.println("Press 1 for ||  Astitva ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Sukhacha Shodh ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Ashru ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Amrutvel ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Gathod ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Astitva  *");
				System.out.println(" ");
				System.out.println("      Author name  Sudha Murti");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Astitva");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Sukhacha Shodh  *");
				System.out.println(" ");
				System.out.println("      Author name  V.S.khadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹144");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 144;
					l.add("Sukhacha Shodh");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionMarathi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Ashru  *");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Gokhale");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("Ashru");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionMarathi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Amrutvel  * ");
				System.out.println(" ");
				System.out.println("      Author name  V.S.Khadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹250");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 250;
					l.add("Amrutvel");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionMarathi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Gathod * ");
				System.out.println(" ");
				System.out.println("      Author name  P.L.Deshpande");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("Gathod");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionMarathi();
				}
				break;
			case 6:
				classicFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				classicFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			classicFictionMarathi();
		}
		sc.close();
	}

	public void classicFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  =|");
		System.out.println("|                                                                             |");
		System.out.println("|             ...(*_*)    English Classic Fiction  Books  (*_*)...            |");
		System.out.println("|                                                                             |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  =|");
		System.out.println(" ");
		System.out.println("Press 1 for ||  Jane Eyre");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Little Women ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Moby-Dick");
		System.out.println(" ");
		System.out.println("Press 4 for ||  The Godfather ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Midnight's Children ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Jane Eyre *");
				System.out.println(" ");
				System.out.println("      Author name  H.G.Well");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Jane Eyre");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionEnglish();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Little Women  * ");
				System.out.println(" ");
				System.out.println("      Author name   John Joseph Adams");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹1210");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 1210;
					l.add("Little Women");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionEnglish();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Moby-Dick *");
				System.out.println(" ");
				System.out.println("      Author name  Akshat Gupta");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
					l.add("Moby-Dick");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionEnglish();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  The Godfather  *");
				System.out.println(" ");
				System.out.println("      Author name   Kevin Missal ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
					l.add("he Godfather");
				}
//				else if(n==2) {	
//				}
				else {
					classicFictionEnglish();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Midnight's Children  *");
				System.out.println(" ");
				System.out.println("      Author name   Conan Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹180");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 180;
					l.add("Midnight's Children");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionEnglish();
				}
				break;
			case 6:
				classicFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				classicFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			classicFictionEnglish();
		}
		sc.close();
	}

	public void classicFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("|                                                                           |");
		System.out.println("|            ...(*_*)   Hindi Classic Fiction  Books  (*_*)...              |");
		System.out.println("|                                                                           |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println(" ");
		System.out.println("Press 1 for ||  Apsara ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Mujhe Chand Chahiye ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Karmabhoomi ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Raag Darbari");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Kamayabi");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Apsara  *");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Tripathi");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 2 For WISHLIST ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
					l.add("Apsara");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionHindi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Mujhe Chand Chahiye ");
				System.out.println(" ");
				System.out.println("      Author name  Sureshchandr Kadam");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹235");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 235;
					l.add("Mujhe Chand Chahiye");
				}
//			else if(n==2) {
//			}
				else {
					classicFictionHindi();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Karmabhoomi  *");
				System.out.println(" ");
				System.out.println("      Author name  Tarun jaulii");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹290");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 290;
					l.add("Karmabhoomi");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionHindi();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Raaj Darbari  *");
				System.out.println(" ");
				System.out.println("      Author name  Sanjay Kumar paal");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹275");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 275;
					l.add("Raaj Darbari");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionHindi();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Kamayabi  *");
				System.out.println(" ");
				System.out.println("      Author name  Kalli Shankar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹420");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//				System.out.println("      Press 2 For WISHLIST ");
//				System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 420;
					l.add("Kamayabi");
				}
//				else if(n==2) {
//				}
				else {
					classicFictionHindi();
				}
				break;
			case 6:
				classicFiction();
				break;
			default:
				System.out.println("You have selected wrong option");
				classicFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			classicFictionHindi();
		}
	}
}

class Horrorfiction extends Fiction {
	public void horrorFiction() {
		System.out.println("!!!...........HORROR FICTION...........!!!");
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Marathi");
		System.out.println("Press 2 for English");
		System.out.println("Press 3 for Hindi");
		System.out.println("Press 4 to previous Books Type");
		int n = sc.nextInt();
		switch (n) {
		case 1:
			horrorFictionMarathi();
			break;
		case 2:
			horrorFictionEnglish();
			break;
		case 3:
			horrorFictionHindi();
			break;
		case 4:
			Fiction f = new Fiction();
			f.fiction();
			break;
		default:
			System.err.println("Please try again");
			horrorFiction();
			break;
		}
		sc.close();
	}

	public void horrorFictionMarathi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                              |");
		System.out.println("|               ...(*_*)  Mararthi Horror Fiction Books  (*_*)...              |");
		System.out.println("|                                                                              |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Savdhan ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Pathlag");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Bahurupi: Kadambari ");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Aghori Hiravat ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Saitan ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Savdhan  *");
				System.out.println(" ");
				System.out.println("      Author name  Narayan  Jogi");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");

				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
					l.add("Savdhan");
				}
//			else if(n==2) {
//			}
				else {
					horrorFictionMarathi();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Pathlag  *");
				System.out.println(" ");
				System.out.println("      Author name  Sureshchandr Bhosale");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹110");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
//			System.out.println("      Press 2 For WISHLIST ");
//			System.out.println(" ");
				System.out.println("      Press 2 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 110;
					l.add("");
				}
//			else if(n==2) {
//			}
				else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Bahurupi: Kadambari  *");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Savant");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Aghori Hiravat  * ");
				System.out.println(" ");
				System.out.println("      Author name  Dr.Bal Falake");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹250");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 250;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Saitan  * ");
				System.out.println(" ");
				System.out.println("      Author name  Narayan Jogalekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
				}  else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 6:
				horrorFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				horrorFictionMarathi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			horrorFictionMarathi();
		}
		sc.close();
	}

	public void horrorFictionEnglish() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == ==  |");
		System.out.println("|                                                                           |");
		System.out.println("|              (*_*)   English Horror Fiction Books  (*_*)...               |");
		System.out.println("|                                                                           |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
		System.out.println("");
		System.out.println("Press 1 for ||  That Night: Four Friends, Twenty Years ");
		System.out.println(" ");
		System.out.println("Press 2 for ||  The Canterville Ghost ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Ghosts of The Silent Hills");
		System.out.println(" ");
		System.out.println("Press 4 for ||  The Girl in the House ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  The Night Circus ");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		System.out.println(" ");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  That Night: Four Friends, Twenty Years  *");
				System.out.println(" ");
				System.out.println("      Author name  Nidhi Well");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹470");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 470;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  The Canterville Ghost  *");
				System.out.println(" ");
				System.out.println("      Author name   Oscar Adams");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹210");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 210;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Ghosts of The Silent Hills  *");
				System.out.println(" ");
				System.out.println("      Author name  Anita Krishan");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹380");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 380;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  The Girl in the House  *");
				System.out.println(" ");
				System.out.println("      Author name   Bram Missal ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹280");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 280;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  The Night Circus  *");
				System.out.println(" ");
				System.out.println("      Author name   Erin Doyle ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹180");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 180;
				}else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 6:
				horrorFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				horrorFictionEnglish();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			horrorFictionEnglish();
		}
		sc.close();
	}

	public void horrorFictionHindi() {
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                                              |");
		System.out.println("|            ...(*_*)   Hindi Horror Fiction Books  (*_*)...                   |");
		System.out.println("|                                                                              |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("");
		System.out.println("Press 1 for ||  Khaali Makan");
		System.out.println(" ");
		System.out.println("Press 2 for ||  Woh Bhayanak Raat  ");
		System.out.println(" ");
		System.out.println("Press 3 for ||  Bhootnath");
		System.out.println(" ");
		System.out.println("Press 4 for ||  Kamini Returns ");
		System.out.println(" ");
		System.out.println("Press 5 for ||  Khamosh... wo laut aaya hai");
		System.out.println(" ");
		System.out.println("Press 6 to go back");
		Scanner sc = new Scanner(System.in);
		try {
			int n = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Khaali Makan  *");
				System.out.println(" ");
				System.out.println("      Author name  Surendra pathak");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹270");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 270;
				}  else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 2:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Woh Bhayanak Raat  *");
				System.out.println(" ");
				System.out.println("      Author name  Sureshchandr Nadekar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹235");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 235;
				}  else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 3:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Bhootnath  *");
				System.out.println(" ");
				System.out.println("      Author name  Babu Khari ");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹290");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 290;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 4:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  * Kamini Returns  * ");
				System.out.println(" ");
				System.out.println("      Author name  Rahul Saini");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹275");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 275;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 5:
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println("|");
				System.out.println("      You have selected :~  *  Khamosh... wo laut aaya hai  *");
				System.out.println(" ");
				System.out.println("      Author name  Kalli Shankar");
				System.out.println(" ");
				System.out.println("      the price of  Book is Rs ₹420");
				System.out.println("|");
				System.out.println(
						"==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==");
				System.out.println(" ");
				System.out.println("      Press 1 For BUY NOW ");
				System.out.println(" ");
				System.out.println("      Press 3 For Chose Another book if you don't like ");
				System.out.println(
						"--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- --");
				System.out.println(" ");
				n = sc.nextInt();
				if (n == 1) {
					bill = bill + 420;
				} else {
					Fiction f = new Fiction();
					f.fiction();
				}
				break;
			case 6:
				horrorFiction();
				break;
			default:
				System.err.println("You have selected wrong option");
				horrorFictionHindi();
				break;
			}

			System.out.println("Do you want to choose another Book?");
			System.out.println("If yes then press Y and for No press N");
			char ch = sc.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				Books b = new Books();
				b.booksInfo();
			} else {
				totalBill();
			}
		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			horrorFictionHindi();
		}
		sc.close();
	}
}

class WithoutRegistrationLoginException extends NullPointerException { // Custom Exception..
	public WithoutRegistrationLoginException(String message) {
		super(message);

	}
}

public class Driver2 {
	static {
		System.out.println("| ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==|");
		System.out.println("|                                                               |");
		System.out.println("|              (^_^)  Welcome to Our Book Store  (^_^)          |");
		System.out.println("|                                                               |");
		System.out.println("|==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  ==  == |");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Registration");
		System.out.println("Press 2 for Login");
		System.out.println("Press 3 for Exit");
		try {
			int n = sc.nextInt();
			Registration r = new Register(); // upCasting
			switch (n) {
			case 1:
				r.signUp();

				break;
			case 2:
				r.login();
				break;
			case 3:
				System.out.println("Thank you for visiting Us");
				System.exit(0);
				break;
			default:
				System.err.println("Please enter the correct input");
				main(null); // main method calling statements
				break;
			}

		} catch (InputMismatchException e) {
			System.err.println("Alphabets are not allowed  (-_-) ");
			System.err.println("Please Enter Given Choice and Try Again..");
			main(null);
		}
		sc.close();
	}
}


