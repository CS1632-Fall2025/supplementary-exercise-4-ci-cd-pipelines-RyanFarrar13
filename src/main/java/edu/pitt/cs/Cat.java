package edu.pitt.cs;

import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*; 

public interface Cat {
	public static Cat createInstance(InstanceType type, int id, String name) {
		switch (type) {
			case IMPL:
				return new CatImpl(id, name);
			case BUGGY:
				return new CatBuggy(id, name);
			case SOLUTION:
				return new CatSolution(id, name);
			case MOCK:
			    // TODO: Return a mock object that emulates the behavior of the real object, if you feel you need one.
				Cat mock = Mockito.mock(Cat.class); //create a mock of cats
				final int Id = id;
    			final String[] Name = { name };
    			final boolean[] IsRented = { false };
				//using a one element array allows me to change the elemnts of the arry even though the array itself cant 

				when(mock.getId()).thenReturn(Id);//all behaviors 
				when(mock.getName()).thenAnswer(inv -> Name[0]);
				when(mock.getRented()).thenAnswer(inv -> IsRented[0]);
				when(mock.toString()).thenAnswer(inv -> "ID " + Id + ". " + Name[0]);

				doAnswer(inv -> { IsRented[0] = true;  return null; }).when(mock).rentCat();
				doAnswer(inv -> { IsRented[0] = false; return null; }).when(mock).returnCat();


				doAnswer(inv -> {String newName = inv.getArgument(0);Name[0] = newName;
        		return null;}).when(mock).renameCat(anyString());
				
				
				return mock;

		    default:
            assert false : "Unknown InstanceType: " + type;
            return null;
		}
		
	}
	

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.
	
	public void rentCat();

	public void returnCat();

	public void renameCat(String name);

	public String getName();

	public int getId();

	public boolean getRented();

	public String toString();
}
