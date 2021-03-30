package userinterface;

import impresario.IModel;
import java.util.*;

//==============================================================================
public class ViewFactory {

	public static View createView(String viewName, IModel model)
	{
		if(viewName.equals("LibrarianView") == true)
		{
			return new LibrarianView(model);
		}
		else if(viewName.equals("BookCollectionView") == true)
		{
			return new BookCollectionView(model);
		}
		else if(viewName.equals("PatronCollectionView") == true)
		{
			return new PatronCollectionView(model);
		}
		else if(viewName.equals("BookSearchView") == true)
		{
			return new BookSearchView(model);
		}
		else if(viewName.equals("PatronSearchView") == true)
		{
			return new PatronSearchView(model);
		}
		else
			return null;
	}

	public static Vector createVectorView(String viewName, IModel model)
	{
		if(viewName.equals("SOME VIEW NAME") == true)
		{
			//return [A NEW VECTOR VIEW OF THAT NAME TYPE]
			return null;
		}
		else
			return null;
	}

}
