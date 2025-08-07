import java.util.*;

class Book 
{
    	private int id;
    	private String title;
    	private String author;
    	private boolean isIssued;

    	public Book(int id, String title, String author) 
	{
        	this.id = id;
        	this.title = title;
        	this.author = author;
        	this.isIssued = false;
    	}

    	public int getId() 
	{ 
		return id; 
	}
    	public String getTitle() 	
	{ 
		return title; 
	}
    	public String getAuthor() 
	{ 
		return author; 
	}
    	public boolean isIssued() 
	{ 
		return isIssued; 
	}

    	public void issue() 
	{ 
		isIssued = true; 
	}
    	public void returnBook() 
	{ 
		isIssued = false; 
	}

    	@Override
    	public String toString() 
	{
        	return id + " | " + title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
    	}
}

class User 
{
    	private int id;
    	private String name;

    	public User(int id, String name) 
	{
        	this.id = id;
        	this.name = name;
    	}

    	public int getId() 
	{ 
		return id; 
	}
    	public String getName() 
	{ 
		return name; 
	}

    	@Override
    	public String toString() 
	{
        	return id + " | " + name;
    	}
}

class Library 
{
    	private ArrayList<Book> books = new ArrayList<>();
    	private ArrayList<User> users = new ArrayList<>();
    	private Map<Integer, Integer> issuedBooks = new HashMap<>();

    	public void addBook(Book book) 
	{
        	books.add(book);
        	System.out.println("Book added.");
    	}

    	public void registerUser(User user) 
	{
        	users.add(user);
        	System.out.println("User registered.");
    	}

    	public void viewBooks() 
	{
        	if (books.isEmpty()) 
		{
            		System.out.println("No books in the library.");
        	} 
		else 
		{
            		books.forEach(System.out::println);
        	}
    	}

    	public void viewUsers() 
	{
        	if (users.isEmpty()) 
		{
            		System.out.println("No users registered.");
        	} 
		else 
		{
            		users.forEach(System.out::println);
        	}
    	}

    	public void issueBook(int bookId, int userId) 
	{
        	for (Book b : books) 
		{
            		if (b.getId() == bookId) 
			{
                		if (b.isIssued()) 
				{
                    			System.out.println("Book already issued.");
                    			return;
                		}

                		b.issue();
                		issuedBooks.put(bookId, userId);
                		System.out.println("Book issued to user ID: " + userId);
                		return;
            		}
        	}
        	System.out.println("Book not found.");
    	}

    	public void returnBook(int bookId) 
	{
        	for (Book b : books) 
		{
            		if (b.getId() == bookId && b.isIssued()) 
			{
                		b.returnBook();
                		issuedBooks.remove(bookId);
                		System.out.println("Book returned.");
                		return;
            		}
        	}
        	System.out.println("Book not found or not issued.");
    	}
}

public class LibraryManagementSystem 
{
    	static Scanner sc = new Scanner(System.in);
    	static Library library = new Library();

    	public static void main(String[] args) 
	{
        	int choice;
        	do 
		{
            		System.out.println("\n=== Library Management System ===");
            		System.out.println("1. Add Book");
            		System.out.println("2. Register User");
            		System.out.println("3. View Books");
            		System.out.println("4. View Users");
            		System.out.println("5. Issue Book");
            		System.out.println("6. Return Book");
            		System.out.println("7. Exit");
            		System.out.print("Enter your choice: ");
            		choice = sc.nextInt();

            		switch (choice) 
			{
                		case 1: 
					addBook(); 
					break;
                		case 2: 
					registerUser(); 
					break;
                		case 3: 	
					library.viewBooks(); 
					break;
                		case 4: 
					library.viewUsers(); 
					break;
                		case 5: 
					issueBook(); 
					break;
                		case 6: 
					returnBook(); 
					break;
                		case 7: 
					System.out.println("Exiting..."); 
					break;
                		default: 
					System.out.println("Invalid choice.");
            		}
        	} while (choice != 7);
    	}

    	private static void addBook() 
	{
        	System.out.print("Enter Book ID: ");
        	int id = sc.nextInt();
        	sc.nextLine();
        	System.out.print("Enter Title: ");
        	String title = sc.nextLine();
        	System.out.print("Enter Author: ");
        	String author = sc.nextLine();
        	library.addBook(new Book(id, title, author));
    	}

    	private static void registerUser() 
	{
        	System.out.print("Enter User ID: ");
        	int id = sc.nextInt();
        	sc.nextLine();
        	System.out.print("Enter Name: ");
        	String name = sc.nextLine();
        	library.registerUser(new User(id, name));
    	}

    	private static void issueBook() 
	{
        	System.out.print("Enter Book ID to issue: ");
        	int bookId = sc.nextInt();
        	System.out.print("Enter User ID to issue to: ");
        	int userId = sc.nextInt();
        	library.issueBook(bookId, userId);
    	}

    	private static void returnBook() 
	{
        	System.out.print("Enter Book ID to return: ");
        	int bookId = sc.nextInt();
        	library.returnBook(bookId);
    	}
}