/**
 * 
 */
package com.ss.lms.biz;

//import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuffer;

/**
 * TODO:	each class loads the text file into JVM at start (into maps by ID)
 * 			any reads/updates/deletes are then based from there
 * 			upon exit or upon explicit user save, modify the save file (remember the user will never see it)
 * 			
 */

/**
 * @author Kyle Sneeden
 *
 */
public class LMS {

	/**
	 * @param args
	 */

	// private static Scanner scan = new Scanner(System.in);
	private static HashMap<Integer, Author> authors = new HashMap<Integer, Author>();
	private static HashMap<Integer, Publisher> publishers = new HashMap<Integer, Publisher>();
	private static HashMap<Integer, Book> books = new HashMap<Integer, Book>();
	private static Stack<Integer[]> menuConstraints = new Stack<Integer[]>();
	private static Stack<Integer> inputInteger = new Stack<Integer>();
	private static ArrayList<StringBuffer> inputString = new ArrayList<StringBuffer>();
	// private static ArrayList<Integer> inputInteger = new ArrayList<Integer>();

	public static void main(String[] args) {
		load();

		try (Scanner scan = new Scanner(System.in)) {
			// MAIN MENU
			// menuConstraints = driverUIMain(scan);
			driverUIMain(scan);

			/*
			 * input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			 * 
			 * while(input.get(input.size() - 1) == -1) { System.out.println("Last Input: "
			 * + input.get(input.size() - 1).toString()); input.pop(); Integer newInput =
			 * getUserInput(scan, menuConstraints[0], menuConstraints[1]);
			 * System.out.println("New Input: " + newInput); input.push(newInput); }
			 * 
			 * //BAD INPUT; MENU BACK TO MAIN MENU if(input.get(input.size() - 1) == -1) {
			 * System.out.println("Only integers between " + menuConstraints[0] + " and " +
			 * menuConstraints[1] + " are allowed. Try input again."); input.pop();
			 * menuConstraints = driverUIMain(scan); } //BOOKS MENU else
			 * if(input.get(input.size() - 1) == 1) { menuConstraints = driverUIBooks();
			 * input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			 * //System.out.println("Your input was: " + input.toString()); //BAD INPUT;
			 * BACK TO BOOKS MENU if(input.get(input.size() - 1) == -1) {
			 * System.out.println("Only integers between " + menuConstraints[0] + " and " +
			 * menuConstraints[1] + " are allowed. Try input again."); input.pop();
			 * menuConstraints = driverUIBooks(); } //CREATE A BOOK MENU else
			 * if(input.get(input.size() - 1) == 1) {
			 * System.out.println("Create a book. Enter title: "); inputString.add(new
			 * StringBuffer(getUserInput(scan))); System.out.println("Enter author: ");
			 * Author newAuthor = new Author(new StringBuffer(getUserInput(scan)));
			 * authors.put(newAuthor.getAuthorID(), newAuthor); //inputString.add(new
			 * StringBuffer(getUserInput(scan)));
			 * System.out.println("Enter publisher name: "); inputString.add(new
			 * StringBuffer(getUserInput(scan)));
			 * System.out.println("Enter publisher address: "); inputString.add(new
			 * StringBuffer(getUserInput(scan))); Publisher newPublisher = new
			 * Publisher(inputString.get(0), inputString.get(1));
			 * publishers.put(newPublisher.getPublisherID(), newPublisher); Book newBook =
			 * new Book(inputString.get(0), newAuthor.getAuthorID(),
			 * newPublisher.getPublisherID()); books.put(newBook.getBookID(), newBook);
			 * menuConstraints = driverUIBooks(); } //READ ALL THEN BACK TO BOOKS MENU else
			 * if(input.get(input.size() - 1) == 2) { readBooks(); menuConstraints =
			 * driverUIBooks(); } //UPDATE BOOK else if(input.get(input.size() - 1) == 3) {
			 * System.out.println("Enter the ID of the book to update: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString())); Book newBook =
			 * books.get(input.get(input.size() - 1));
			 * System.out.println("Enter the new title of the book: "); StringBuffer title =
			 * getUserInput(scan); System.out.println("Enter the new author of the book: ");
			 * StringBuffer authorName = getUserInput(scan);
			 * System.out.println("Enter the new publisher name of the book: ");
			 * StringBuffer publisherName = getUserInput(scan);
			 * System.out.println("Enter the new publisher address of the book: ");
			 * StringBuffer address = getUserInput(scan); updateBook(newBook, title,
			 * authorName, publisherName, address); } //DELETE BOOK BY ID else
			 * if(input.get(input.size() - 1) == 4) {
			 * System.out.println("Enter ID of book to delete: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString()));
			 * deleteBook(input.get(input.size() - 1)); } //SAVE BOOK else
			 * if(input.get(input.size() - 1) == 5) { save(); menuConstraints =
			 * driverUIBooks();
			 * 
			 * } //GO BACK else if(input.get(input.size() - 1) == 6) { menuConstraints =
			 * driverUIBooks(); } } //AUTHORS MENU else if(input.get(input.size() - 1) == 2)
			 * { menuConstraints = driverUIAuthors(); input.push(getUserInput(scan,
			 * menuConstraints[0], menuConstraints[1])); //BAD INPUT MENU; BACK TO AUTHORS
			 * MENU if(input.get(input.size() - 1) == -1) { menuConstraints =
			 * driverUIAuthors(); } //CREATE AN AUTHOR MENU else if(input.get(input.size() -
			 * 1) == 1) { System.out.println("Enter author name: "); Author newAuthor = new
			 * Author(new StringBuffer(getUserInput(scan)));
			 * authors.put(newAuthor.getAuthorID(), newAuthor); } //READ ALL AUTHORS MENU
			 * else if(input.get(input.size() - 1) == 2) { readAuthors(); menuConstraints =
			 * driverUIAuthors(); } //UPDATE AN AUTHOR MENU else if(input.get(input.size() -
			 * 1) == 3) {
			 * 
			 * System.out.println("Enter the ID of the book to update: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString())); Integer newID =
			 * input.get(input.size() - 1);
			 * System.out.println("Enter the new author's name: "); getUserInput(scan);
			 * updateAuthor(authors.get(input.get(input.size() - 1)), );
			 * 
			 * Author newAuthor = authors.get(input.get(input.size() - 1));
			 * System.out.println("Enter the new title of the book: "); StringBuffer title =
			 * getUserInput(scan);
			 * 
			 * 
			 * } //DELETE AN AUTHOR MENU else if(input.get(input.size() - 1) == 4) {
			 * System.out.println("Enter ID of the author to delete: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString()));
			 * deleteAuthor(input.get(input.size() - 1)); } //SAVE; BACK TO AUTHORS MENU
			 * else if(input.get(input.size() - 1) == 5) { save(); menuConstraints =
			 * driverUIAuthors(); } //BACK TO MENU else if(input.get(input.size() - 1) == 6)
			 * { menuConstraints = driverUIAuthors(); } else { menuConstraints =
			 * driverUIAuthors(); } } //PUBLISHERS MENU else if(input.get(input.size() - 1)
			 * == 3) { driverUIPublishers(); menuConstraints = driverUIPublishers();
			 * input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1])); //BAD
			 * INPUT MENU; BACK TO PUBLSHERS MENU if(input.get(input.size() - 1) == -1) {
			 * menuConstraints = driverUIAuthors(); } //CREATE A PUBLSHER MENU else
			 * if(input.get(input.size() - 1) == 1) {
			 * System.out.println("Enter publisher name: "); inputString.add(new
			 * StringBuffer(getUserInput(scan))); //scan.hasNextLine();
			 * System.out.println("Enter publisher address: "); inputString.add(new
			 * StringBuffer(getUserInput(scan))); Publisher newPublisher = new
			 * Publisher(inputString.get(0), inputString.get(1));
			 * publishers.put(newPublisher.getPublisherID(), newPublisher); } //READ ALL
			 * PUBLSHERS MENU else if(input.get(input.size() - 1) == 2) { readPublishers();
			 * menuConstraints = driverUIPublishers(); } //UPDATE A PUBLSHER MENU else
			 * if(input.get(input.size() - 1) == 3) {
			 * System.out.println("Enter ID of publisher to update: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString())); //updatePublisher(); }
			 * //DELETE A PUBLSHERS MENU else if(input.get(input.size() - 1) == 4) {
			 * System.out.println("Enter ID of publisher to delete: "); input.push(new
			 * Integer(getUserIntegerInput(scan).toString()));
			 * deletePublisher(input.get(input.size() - 1)); } //SAVE; BACK TO PUBLSHERS
			 * MENU else if(input.get(input.size() - 1) == 5) { save(); menuConstraints =
			 * driverUIPublishers(); } //BACK TO MENU else if(input.get(input.size() - 1) ==
			 * 6) { menuConstraints = driverUIPublishers(); } else { menuConstraints =
			 * driverUIPublishers(); }
			 * 
			 * } //SAVE THEN BACK TO MAIN MENU else if(input.get(input.size() - 1) == 4) {
			 * save(); menuConstraints = driverUIMain(); } //EXIT PROGRAM else
			 * if(input.get(input.size() - 1) == 5) { exit(); } //UNKNOWN BAD INPUT; BACK TO
			 * MAIN MENU else { System.out.println("Only integers between " +
			 * menuConstraints[0] + " and " + menuConstraints[1] +
			 * " are allowed. Try input again."); input.pop(); menuConstraints =
			 * driverUIMain(); }
			 */ 
			} catch (InputMismatchException ime) {
				System.err.println("Please run it again with legal input.");
				//driverUIMain(scan);
			} catch (Exception e) {
				System.err.println("Something went wrong getting user input");
				e.printStackTrace(System.err);		
				//driverUIMain(scan);
			}

		// System.out.println("Your input was: " + input.toString());

		// deleteAuthor(2);
		// deletePublisher(3);
		// addAuthor(new StringBuffer("John Smith"));
		// addPublisher(new StringBuffer("John's Publishing House"), new
		// StringBuffer("1010 Springfield Ave, NP, NJ, USA"));
		// addBook(new StringBuffer("A New Book"), new StringBuffer("New Author"), new
		// StringBuffer("New Publisher"), new StringBuffer("555 New St, New, NY, USA"));
		// updateAuthor(authors.get(1), new StringBuffer("An Updated Name"));
		// updatePublisher(publishers.get(2), new StringBuffer("Updated Publisher
		// Name"), new StringBuffer("Updated Publisher Address, USA"));
		// updateBook(books.get(6), new StringBuffer("An Updated Title"), new
		// StringBuffer("Updated Author Name"), new StringBuffer("Updated Pubisher
		// Name"), new StringBuffer("This Updated Address, USA"));
		save();

		/*
		 * listBooks(); System.out.println(); listAuthors(); System.out.println();
		 * listPublishers();
		 */

		/*
		 * dbDriverMain(); Integer commandType = scan.nextInt();
		 * 
		 * if(commandType == 1) { dbDriver2("Create"); } else if(commandType == 1) {
		 * dbDriver2("Read"); } else if(commandType == 3) { dbDriver2("Update"); } else
		 * if(commandType == 4) { dbDriver2("Delete"); } else { dbDriverMain(); }
		 */
		/*
		 * THIS IS FOR WEEK 2 mainMenu(); Integer userType = scan.nextInt();
		 * 
		 * switch(userType){ case 1: lib1(); break; case 2: admin1(); break; case 3:
		 * borr1(); break;
		 * 
		 * }
		 */
	}

	private static Book addBook(StringBuffer title, StringBuffer authorName, StringBuffer publisherName, StringBuffer address) {
		Author bookAuthor = addAuthor(authorName);
		Publisher bookPublisher = addPublisher(publisherName, address);
		
		Book newBook = new Book(title, bookAuthor.getAuthorID(), bookPublisher.getPublisherID());
		Book[] bookArray = books.values().toArray(new Book[0]);
		Book book = null;
		for (Integer i = 0; i < bookArray.length; i++) {
			if (bookArray[i].equals(newBook)) {
				System.out.println("That book already exists");
				return bookArray[i];
			}
		}

		books.put(newBook.getBookID(), newBook);
		System.out.println("Book added");
		return newBook;
	}

	private static Author addAuthor(StringBuffer authorFullName) {
		for (HashMap.Entry<Integer, Author> currentAuthor : authors.entrySet()) {			
			if (currentAuthor.getValue().getAuthorName().toString().equals(authorFullName.toString())) {
				System.out.println("That author already exists");
				return currentAuthor.getValue();
			}
		}
		Author newAuthor = new Author(authorFullName);
		authors.put(newAuthor.getAuthorID(), newAuthor);
		System.out.println("Author added");
		return newAuthor;
	}

	private static Publisher addPublisher(StringBuffer publisherName, StringBuffer address) {
		for (HashMap.Entry<Integer, Publisher> currentPublisher : publishers.entrySet()) {
			if (currentPublisher.getValue().getPublisherName().toString().equals(publisherName.toString())
					&& currentPublisher.getValue().getAddress().toString().equals(address.toString())) {
				System.out.println("That publisher already exists");
				return currentPublisher.getValue();
			}
		}
		Publisher newPublisher = new Publisher(publisherName, address);
		publishers.put(newPublisher.getPublisherID(), newPublisher);
		System.out.println("Publisher added");
		return newPublisher;
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static void readBooks() {
		for (HashMap.Entry<Integer, Book> currentBook : books.entrySet()) {
			Integer authorID = currentBook.getValue().getAuthorID();
			Integer publisherID = currentBook.getValue().getPublisherID();
			System.out.println("ID: " + currentBook.getKey() + " | Title: " + currentBook.getValue().getTitle()
					+ " | Written by: " + authors.get(authorID).getAuthorName() + " | Published by: "
					+ publishers.get(publisherID).getPublisherName());
		}
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static void readAuthors() {
		for (HashMap.Entry<Integer, Author> currentAuthor : authors.entrySet()) {
			System.out
					.println("ID: " + currentAuthor.getKey() + " | Name: " + currentAuthor.getValue().getAuthorName());
		}
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static void readPublishers() {
		for (HashMap.Entry<Integer, Publisher> currentPublisher : publishers.entrySet()) {
			System.out.println(
					"ID: " + currentPublisher.getKey() + " | Name: " + currentPublisher.getValue().getPublisherName()
							+ " | Address: " + currentPublisher.getValue().getAddress());
		}
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static void readBooksByAuthor(Integer authorID) {
		for (HashMap.Entry<Integer, Book> currentBook : books.entrySet()) {
			if (currentBook.getValue().getAuthorID().equals(authorID)) {
				Integer publisherID = currentBook.getValue().getPublisherID();
				System.out.println("ID: " + currentBook.getKey() + " | Title: " + currentBook.getValue().getTitle()
						+ " | Published by: " + publishers.get(publisherID).getPublisherName());
			}
		}

	}

	private static void updateBook(Book book, StringBuffer title, StringBuffer authorName, StringBuffer publisherName, StringBuffer address) 
	{
		Author bookAuthor = addAuthor(authorName);
		Publisher bookPublisher = addPublisher(publisherName, address); 
		for (HashMap.Entry<Integer, Book> currentBook : books.entrySet()) {
			if(currentBook.getValue().getTitle().toString().equals(title.toString()) && 
					currentBook.getValue().getAuthorID() == bookAuthor.getAuthorID() &&
					currentBook.getValue().getPublisherID() == bookPublisher.getPublisherID()) {
						System.out.println("That author already exists. Record not updated.");
						return;
			}
		}		
		
		book.setTitle(title);
		book.setAuthorID(bookAuthor.getAuthorID());
		book.setPublisherID(bookPublisher.getPublisherID());
		//System.out.println("Book updated"); //printed from the UI driver
		}

	private static void updateAuthor(Author author, StringBuffer authorName) {
		for (HashMap.Entry<Integer, Author> currentAuthor : authors.entrySet()) {
			if (currentAuthor.getValue().getAuthorName().toString().equals(authorName.toString())) {
				System.out.println("That author already exists. Record not updated.");
				return;
			}
		}
		author.setAuthorName(authorName);
		//System.out.println("Author updated"); //printed from the UI driver
	}

	private static void updatePublisher(Publisher publisher, StringBuffer publisherName, StringBuffer address) {
		for (HashMap.Entry<Integer, Publisher> currentPublisher : publishers.entrySet()) {
			if (currentPublisher.getValue().getPublisherName().toString().equals(publisherName.toString())
					&& currentPublisher.getValue().getAddress().toString().equals(address.toString())) {
				System.out.println("That publisher already exists. Record not updated.");
				return;
			}
		}
		publisher.setPublisherName(publisherName);
		publisher.setAddress(address);
		//System.out.println("Publisher updated"); //printed from the UI driver
	}

	private static Book deleteBook(Integer bookID) {
		return books.remove(bookID);
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static Author deleteAuthor(Integer authorID) {
		Collection<Book> bookValues = books.values();
		Iterator<Book> bookIterator = bookValues.iterator();
		while (bookIterator.hasNext()) {
			Book nextBook = bookIterator.next();
			if (nextBook.getAuthorID().equals(authorID)) {
				bookIterator.remove();
			}
		}
		return authors.remove(authorID);
	}

	// THIS IS A GOOD CANDIDATE TO CONVERT TO LAMDAS/STREAMS
	private static Publisher deletePublisher(Integer publisherID) {
		Collection<Book> bookValues = books.values();
		Iterator<Book> bookIterator = bookValues.iterator();
		while (bookIterator.hasNext()) {
			Book nextBook = bookIterator.next();
			if (nextBook.getPublisherID().equals(publisherID)) {
				bookIterator.remove();
			}
		}
		return publishers.remove(publisherID);
	}

	private static void load() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Authors.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				StringBuffer rawAuthorData = new StringBuffer(line);
				Integer authorID = new Integer(rawAuthorData.substring(0, rawAuthorData.indexOf("|")));
				StringBuffer authorName = new StringBuffer(rawAuthorData.substring(rawAuthorData.indexOf("|") + 1));
				Author currentAuthor = new Author(authorID, authorName);
				authors.put(authorID, currentAuthor);
			}

			br = new BufferedReader(new FileReader(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Publishers.txt"));
			while ((line = br.readLine()) != null) {
				Integer lastDelimiter = 0;
				StringBuffer rawPublisherData = new StringBuffer(line);
				Integer publisherID = new Integer(
						rawPublisherData.substring(lastDelimiter, rawPublisherData.indexOf("|")));
				lastDelimiter = rawPublisherData.indexOf("|");
				StringBuffer businessName = new StringBuffer(rawPublisherData.substring(lastDelimiter + 1,
						rawPublisherData.indexOf("|", lastDelimiter + 1)));
				lastDelimiter = rawPublisherData.indexOf("|", lastDelimiter + 1);
				StringBuffer address = new StringBuffer(rawPublisherData.substring(lastDelimiter + 1));
				Publisher currentPublisher = new Publisher(publisherID, businessName, address);
				publishers.put(publisherID, currentPublisher);
			}

			br = new BufferedReader(new FileReader(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Books.txt"));
			while ((line = br.readLine()) != null) {
				// is this use of StringBuffer[] object oriented?
				Integer lastDelimiter = 0;
				StringBuffer rawBookData = new StringBuffer(line);
				Integer bookID = new Integer(rawBookData.substring(lastDelimiter, rawBookData.indexOf("|")));
				lastDelimiter = rawBookData.indexOf("|");
				StringBuffer title = new StringBuffer(
						rawBookData.substring(lastDelimiter + 1, rawBookData.indexOf("|", lastDelimiter + 1)));
				lastDelimiter = rawBookData.indexOf("|", lastDelimiter + 1);
				Integer authorID = new Integer(
						rawBookData.substring(lastDelimiter + 1, rawBookData.indexOf("|", lastDelimiter + 1)));
				lastDelimiter = rawBookData.indexOf("|", lastDelimiter + 1);
				Integer publisherID = new Integer(rawBookData.substring(lastDelimiter + 1));
				Book currentBook = new Book(bookID, title, authorID, publisherID);
				books.put(bookID, currentBook);
			}

		} catch (FileNotFoundException f) {
			System.err.println("File not found");
		} catch (Exception e) {
			System.err.println("Something went wrong");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					System.err.println("Failed to close BufferedReader");
				}

			}

		}
	}

	/**
	 * save data to files
	 */
	private static void save() {
		BufferedWriter bw = null;
		try {
			// bw = new BufferedWriter(new
			// FileWriter("C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\test.txt"));
			// bw.write("This is only a test");
			// bw.flush();
			bw = new BufferedWriter(new FileWriter(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Authors.txt"));
			for (HashMap.Entry<Integer, Author> currentAuthor : authors.entrySet()) {
				// System.out.println(currentAuthor.getValue().toStringBuffer().toString());
				bw.write(currentAuthor.getValue().toStringBuffer().toString());
				bw.newLine();
			}
			bw.flush();

			bw = new BufferedWriter(new FileWriter(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Books.txt"));
			for (HashMap.Entry<Integer, Book> currentBook : books.entrySet()) {
				// System.out.println(currentBook.getValue().toStringBuffer().toString());
				bw.write(currentBook.getValue().toStringBuffer().toString());
				bw.newLine();
			}
			bw.flush();

			bw = new BufferedWriter(new FileWriter(
					"C:\\Users\\UCI\\Documents\\GitHub\\Smoothstack\\src\\com\\ss\\lms\\biz\\Publishers.txt"));
			for (HashMap.Entry<Integer, Publisher> currentPublisher : publishers.entrySet()) {
				// System.out.println(currentPublisher.getValue().toStringBuffer().toString());
				bw.write(currentPublisher.getValue().toStringBuffer().toString());
				bw.newLine();
			}
			bw.flush();
			System.out.println("Progress saved");
		} catch (IOException e) {
			System.err.println("Books.txt cannot be created or opened.");
		} catch (Exception e) {
			System.err.println("Error writing to Books.txt.");
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe) {
					System.err.println("Failed to close BufferedWriter");
				}
			}

		}
	}

	/**
	 * save data to files then exit without error
	 */
	private static void exit() {
		// save(); //give the option to exit without saving
		System.exit(0);
	}

	private static StringBuffer getUserInput(Scanner scan) {
		StringBuffer input = new StringBuffer();
		// if(scan.hasNextLine())
		// {
		// scan.nextLine();
		String inputString = scan.nextLine();
		input.append(inputString);
		if (input.length() >= 2) {
			return input;
		}
		System.out.println("Input must be at least 2 characters long");
		// }
		return input;
	}

	private static Integer getUserIntegerInput(Scanner scan, HashMap elements) {
		Integer input = -1;
		if(!scan.hasNextInt())
		{
			scan.nextLine();
			return -1;
		}
		input = scan.nextInt();
		scan.nextLine();			
		if(elements.containsKey(input))
		{
			return input;
		}
		else
		{
			return -1;
		}
		
	}

	private static Integer getUserIntegerInput(Scanner scan, Stack<Integer[]> menuConstraints) {
		Integer input = -1;
		if (!scan.hasNextInt()) {
			scan.nextLine();
			return -1;
		}
		input = scan.nextInt();
		scan.nextLine();
		Integer low = menuConstraints.get(menuConstraints.size() - 1)[0];
		Integer high = menuConstraints.get(menuConstraints.size() - 1)[1];
		if (input >= low && input <= high) {
			return input;
		}
		return -1;
	}

	// MAIN MENU
	private static void driverUIMain(Scanner scan) {
		System.out.println(
				"Welcome to the Smoothstack Library Management System. Would you like to access books, authors, publishers, save your progress, or exit? (enter an integer 1-5 only)");
		System.out.println("1) Books");
		System.out.println("2) Authors");
		System.out.println("3) Publishers");
		System.out.println("4) Save Updates");
		System.out.println("5) Exit");
		Integer[] constraints = { 1, 5 };

		menuConstraints.push(constraints);
		Integer currentInputInteger = getUserIntegerInput(scan, menuConstraints);
		inputInteger.push(currentInputInteger);

		/*
		 * while(input.get(input.size() - 1) == -1) { System.out.println("Last Input: "
		 * + input.get(input.size() - 1).toString()); input.pop(); Integer newInput =
		 * getUserInput(scan, menuConstraints[0], menuConstraints[1]);
		 * System.out.println("New Input: " + newInput); input.push(newInput); }
		 */
		// BAD INPUT; MENU BACK TO MAIN MENU
		if (currentInputInteger == -1) {
			Integer currentMenuConstraints[] = menuConstraints.pop();
			inputInteger.pop();
			System.out.println("Only integers between " + currentMenuConstraints[0] + " and "
					+ currentMenuConstraints[1] + " are allowed. Try input again.");
			driverUIMain(scan);
		}
		// BOOKS MENU
		else if (currentInputInteger == 1) {
			// menuConstraints.push(driverUIBooks(scan));
			driverUIBooks(scan);
			// CREATE A BOOK MENU
		} else if (currentInputInteger == 2) {
			driverUIAuthors(scan);
			// menuConstraints = driverUIAuthors();
			// input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			// BAD INPUT MENU; BACK TO AUTHORS MENU
		}
		else if (currentInputInteger == 3) {
			driverUIPublishers(scan);
			// menuConstraints = driverUIAuthors();
			// input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			// BAD INPUT MENU; BACK TO AUTHORS MENU
		}
		else if (currentInputInteger == 4) {
			save();
			driverUIMain(scan);
			// menuConstraints = driverUIAuthors();
			// input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			// BAD INPUT MENU; BACK TO AUTHORS MENU
		}
		else if (currentInputInteger == 5) {
			exit();
			// menuConstraints = driverUIAuthors();
			// input.push(getUserInput(scan, menuConstraints[0], menuConstraints[1]));
			// BAD INPUT MENU; BACK TO AUTHORS MENU
		}
		else {
			System.out.println("Illegal input. Try again.");
			driverUIMain(scan);
		}

		// return constraints;
	}

	private static void driverUIAuthors(Scanner scan) {
		System.out.println(
				"Welcome to authors. Would you like to create, read, update, delete, save your progress, or go back? (enter an integer 1-6 only)");
		System.out.println("1) Create Author");
		System.out.println("2) Read All Author");
		System.out.println("3) Update Author");
		System.out.println("4) Delete Author");
		System.out.println("5) Save Updates");
		System.out.println("6) Go back");
		Integer[] constraints = { 1, 6 };
		
		menuConstraints.push(constraints);
		Integer currentInputInteger = getUserIntegerInput(scan, menuConstraints);
		inputInteger.push(currentInputInteger);

		if (currentInputInteger == -1) {
			Integer currentMenuConstraints[] = menuConstraints.pop();
			inputInteger.pop();
			System.out.println("Only integers between " + currentMenuConstraints[0] + " and "
					+ currentMenuConstraints[1] + " are allowed. Try input again.");
			driverUIAuthors(scan);
		} else if (currentInputInteger == 1) {
			driverUIAuthorsCreate(scan);
		} else if (currentInputInteger == 2) {
			driverUIAuthorsRead(scan);
		} else if (currentInputInteger == 3) {
			driverUIAuthorsUpdate(scan);
		} else if (currentInputInteger == 4) {
			driverUIAuthorsDelete(scan);
		} else if (currentInputInteger == 5) {
			//ADD SAVEAUTHOR() METHOD
			save();
			driverUIAuthors(scan);
		} else if (currentInputInteger == 6) {
			driverUIMain(scan);
		} else {
			driverUIAuthors(scan);
		}

		// return contraints;
	}

	private static void driverUIBooks(Scanner scan) {
		System.out.println(
				"Welcome to books. Would you like to create, read, update, delete, save your progress, or go back? (enter an integer 1-6 only)");
		System.out.println("1) Create Book");
		System.out.println("2) Read All Books");
		System.out.println("3) Update Book");
		System.out.println("4) Delete Book");
		System.out.println("5) Save Updates");
		System.out.println("6) Go back");
		Integer[] constraints = { 1, 6 };

		menuConstraints.push(constraints);
		Integer currentInputInteger = getUserIntegerInput(scan, menuConstraints);
		inputInteger.push(currentInputInteger);

		// System.out.println("Your input was: " + input.toString());
		// BAD INPUT; BACK TO BOOKS MENU
		if (currentInputInteger == -1) {
			Integer currentMenuConstraints[] = menuConstraints.pop();
			inputInteger.pop();
			System.out.println("Only integers between " + currentMenuConstraints[0] + " and "
					+ currentMenuConstraints[1] + " are allowed. Try input again.");
			driverUIBooks(scan);
		} else if (currentInputInteger == 1) {
			driverUIBooksCreate(scan);
		} else if (currentInputInteger == 2) {
			driverUIBooksRead(scan);
		} else if (currentInputInteger == 3) {
			driverUIBooksUpdate(scan);
		} else if (currentInputInteger == 4) {
			driverUIBooksDelete(scan);
		} else if (currentInputInteger == 5) {
			save();
			driverUIBooks(scan);
		} else if (currentInputInteger == 6) {
			driverUIMain(scan);
		} else {
			driverUIBooks(scan);
		}

		// return constraints;
	}

	private static void driverUIPublishers(Scanner scan) {
		System.out.println(
				"Welcome to publishers. Would you like to create, read, update, delete, save your progress, or go back? (enter an integer 1-6 only)");
		System.out.println("1) Create Publisher");
		System.out.println("2) Read All  Publishers");
		System.out.println("3) Update  Publisher");
		System.out.println("4) Delete  Publisher");
		System.out.println("5) Save Updates");
		System.out.println("6) Go back");
		Integer[] constraints = { 1, 6 };
		
		menuConstraints.push(constraints);
		Integer currentInputInteger = getUserIntegerInput(scan, menuConstraints);
		inputInteger.push(currentInputInteger);

		// System.out.println("Your input was: " + input.toString());
		// BAD INPUT; BACK TO BOOKS MENU
		if (currentInputInteger == -1) {
			Integer currentMenuConstraints[] = menuConstraints.pop();
			inputInteger.pop();
			System.out.println("Only integers between " + currentMenuConstraints[0] + " and "
					+ currentMenuConstraints[1] + " are allowed. Try input again.");
			driverUIPublishers(scan);
		} else if (currentInputInteger == 1) {
			driverUIPublishersCreate(scan);
		} else if (currentInputInteger == 2) {
			driverUIPublishersRead(scan);
		} else if (currentInputInteger == 3) {
			driverUIPublishersUpdate(scan);
		} else if (currentInputInteger == 4) {
			driverUIPublishersDelete(scan);
		} else if (currentInputInteger == 5) {
			save();
			driverUIPublishers(scan);
		} else if (currentInputInteger == 6) {
			driverUIMain(scan);
		} else {
			driverUIPublishers(scan);
		}

		//return contraints;
	}

	private static void driverUIBooksCreate(Scanner scan) {
		System.out.println("Create a book. Enter title: ");
		// inputString.add(getUserInput(scan));
		StringBuffer newTitle = getUserInput(scan);
		System.out.println("Enter author name: ");
		StringBuffer newAuthorName = getUserInput(scan);
		Author newAuthor = addAuthor(newAuthorName);
		// inputString.add(new StringBuffer(getUserInput(scan)));
		System.out.println("Enter publisher name: ");
		// inputString.add(getUserInput(scan));
		StringBuffer newPublisherName = getUserInput(scan);
		System.out.println("Enter publisher address: ");
		// inputString.add(getUserInput(scan));
		StringBuffer newPublisherAddress = getUserInput(scan);
		Publisher newPublisher = addPublisher(newPublisherName, newPublisherAddress);
		Book newBook = addBook(newTitle, newAuthor.getAuthorName(), newPublisher.getPublisherName(), newPublisher.getAddress());
		driverUIBooks(scan);
	}
	
	private static void driverUIAuthorsCreate(Scanner scan) {
		System.out.println("Create an author. Enter author name: ");
		StringBuffer newAuthorName = getUserInput(scan);
		Author newAuthor = addAuthor(newAuthorName);
		driverUIAuthors(scan);
	}
	
	private static void driverUIPublishersCreate(Scanner scan) {
		System.out.println("Create a publisher. Enter publisher name: ");
		StringBuffer newPublisherName = getUserInput(scan);
		System.out.println("Enter publisher address: ");
		StringBuffer newPublisherAddress = getUserInput(scan);
		Publisher newPublisher = addPublisher(newPublisherName, newPublisherAddress);
		driverUIBooks(scan);
	}

	private static void driverUIBooksRead(Scanner scan) {
		readBooks();
		driverUIBooks(scan);
	}
	private static void driverUIAuthorsRead(Scanner scan) {
		readAuthors();
		driverUIAuthors(scan);
	}
	private static void driverUIPublishersRead(Scanner scan) {
		readPublishers();
		driverUIPublishers(scan);
	}
	private static void driverUIBooksUpdate(Scanner scan) {
		System.out.println("Enter the ID of the book to update: ");
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		Integer bookID = getUserIntegerInput(scan, books);
		if(bookID == -1)
		{
			System.out.println("Must enter a valid book ID. Read all books to list valid book IDs.");
			driverUIBooks(scan);
		}
		Book bookToUpdate = books.get(bookID);
		System.out.println("Enter the new title of the book: ");
		StringBuffer newTitle = getUserInput(scan);
		System.out.println("Enter the new author of the book: ");
		StringBuffer newAuthorName = getUserInput(scan);
		System.out.println("Enter the new publisher name of the book: ");
		StringBuffer newPublisherName = getUserInput(scan);
		System.out.println("Enter the new publisher address of the book: ");
		StringBuffer newAddress = getUserInput(scan);
		updateBook(bookToUpdate, newTitle, newAuthorName, newPublisherName, newAddress);
		System.out.println("Book updated");
		driverUIBooks(scan);
	}
	private static void driverUIAuthorsUpdate(Scanner scan) {
		System.out.println("Enter the ID of the author to update: ");
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		Integer authorID = getUserIntegerInput(scan, authors);
		if(authorID == -1)
		{
			System.out.println("Must enter a valid author ID. Read all authors to list valid author IDs.");
			driverUIAuthors(scan);
		}
		Author authorToUpdate = authors.get(authorID);
		System.out.println("Enter the new author name: ");
		StringBuffer newAuthorName = getUserInput(scan);
		updateAuthor(authorToUpdate, newAuthorName);
		System.out.println("Author updated");
		driverUIAuthors(scan);
	}
	private static void driverUIPublishersUpdate(Scanner scan) {
		System.out.println("Enter the ID of the publisher to update: ");
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		Integer publisherID = getUserIntegerInput(scan, publishers);
		if(publisherID == -1)
		{
			System.out.println("Must enter a valid publisher ID. Read all publishers to list valid publisher IDs.");
			driverUIPublishers(scan);
		}
		Publisher publisherToUpdate = publishers.get(publisherID);
		System.out.println("Enter the new publisher name: ");
		StringBuffer newPublisherName = getUserInput(scan);
		System.out.println("Enter the new publisher address: ");
		StringBuffer newPublisherAddress = getUserInput(scan);
		updatePublisher(publisherToUpdate, newPublisherName, newPublisherAddress);
		System.out.println("Publisher updated");
		driverUIPublishers(scan);
	}

	private static void driverUIBooksDelete(Scanner scan) {
		System.out.println("Enter ID of book to delete: ");
		Integer bookID = getUserIntegerInput(scan, books);
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		if (deleteBook(bookID) == null) {
			System.out.println("Invalid book ID. Read All Books for a list of valid book IDs.");
		} else {
			System.out.println("Book deleted");
		}
		driverUIBooks(scan);
	}
	private static void driverUIAuthorsDelete(Scanner scan) {
		System.out.println("Enter ID of Authors to delete: ");
		Integer authorsID = getUserIntegerInput(scan, authors);
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		if (deleteAuthor(authorsID) == null) {
			System.out.println("Invalid publisher ID. Read All Authors for a list of valid author IDs.");
		} else {
			System.out.println("Publisher deleted");
		}
		driverUIAuthors(scan);
	}
	private static void driverUIPublishersDelete(Scanner scan) {
		System.out.println("Enter ID of publisher to delete: ");
		Integer publisherID = getUserIntegerInput(scan, publishers);
		// input.push(new Integer(getUserIntegerInput(scan).toString()));
		if (deletePublisher(publisherID) == null) {
			System.out.println("Invalid publisher ID. Read All Publishers for a list of valid publisher IDs.");
		} else {
			System.out.println("Publisher deleted");
		}
		driverUIPublishers(scan);
	}

	private static void mainMenu() {
		System.out.println(
				"Welcome to the Smoothstack Library Management System. Which category of user are you (enter number only)");
		System.out.println();
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
	}

	public static void lib1() {
		System.out.println("1) Enter the branch you manage");
		System.out.println("2) Quit to previous");
	}

	public static void lib2() {
		System.out.println("1) University Library, Boston");
		System.out.println("2) State Library, New York");
		System.out.println("3) Federal Library, Washington DC");
		System.out.println("4) County Library, McLean VA");
		System.out.println("5) Quit to previous");
	}

	public static void lib3() {
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Quit to previous");
	}

	public static void lib4() {
		System.out.print(
				"You have chosen to update the Branch with Branch Id: X and Branch Name: ABCD. Enter ‘quit’ at any prompt to cancel operation.");
		System.out.print("Please enter new branch name or enter N/A for no change:");
		System.out.print("Please enter new branch address or enter N/A for no change:");
	}

	public static void admin1() {
		System.out.println("1) Add/Update/Delete/Read Book and Author");
		System.out.println("2) Add/Update/Delete/Read Genres");
		System.out.println("3) Add/Update/Delete/Read Publishers");
		System.out.println("4) Add/Update/Delete/Read Library Branches");
		System.out.println("5) Add/Update/Delete/Read Borrowers");
	}

	public static void borr0() {
		System.out.println("Enter the your Card Number:");
	}

	public static void borr1() {
		System.out.println("1) Check out a book");
		System.out.println("2) Return a Book");
		System.out.println("3) Quit to Previous");
	}

	public static void borr2() {
		System.out.println("Pick the Branch you want to check out from:");
		System.out.println("1) University Library, Boston");
		System.out.println("2) State Library, New York");
		System.out.println("3) Federal Library, Washington DC");
		System.out.println("4) County Library, McLean VA");
		System.out.println("5) Quit to previous");
	}

	public static void borr3() {
		System.out.println("1) Lost Tribe by Sidney Sheldon");
		System.out.println("2) The Haunting by Stepehen King");
		System.out.println("3) Microtrends by Mark Penn");
		System.out.println("4) Quit to cancel operation");
	}
}
