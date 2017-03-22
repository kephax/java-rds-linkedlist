import java.util.Iterator;

public class RecList<Type> implements Iterable<Type>
{
	/// ATTRIBUTES ///
	private Type value;
	private RecList<Type> link;
	
	
	/// CONSTRUCTOR ///
	public RecList()
	{
		value = null;
		link = null;
	}

	public RecList( Type aValue, RecList<Type> aLink )
	{
		value = aValue;
		link = aLink;
	}
	
		
	/// METHODS ///
	public boolean isEmpty()
	{
		return link == null; // If Link = null then reutrn true else false
	}
	
	public int size()
	{
		return isEmpty() ? 0 : 1 + link.size(); // Als true dan (?) else (:)
	}
		
	public void prepend( Type object )
	{
		link  = new RecList<Type>( value, link );
		value = object;
	}
		
	public void append( Type object )
	{
		// link.isEmpty() ? prepend( object ) : link.prepend( object ); -> vraag docent
		
		if ( isEmpty() )
		{
			prepend( object );
		}
		else
		{
			link.append( object );
		}
	}
	
	public void addAfter( Type object, Type afterObject )
	{
		if( !isEmpty() )
		{
			if( this.value.equals( afterObject ) )			// If afterObject is found - the object after which we place the new object
			{
				RecList<Type> obj = new RecList<Type>( object, link );
				link = obj; 
			}
			else
			{
				if( !isEmpty() ) // Check if not last element
				{
					link.addAfter( object, afterObject );
				}
			}
		}
	}
	
	public boolean contains( Object aValue )
	{
		if ( !isEmpty() )
		{
			// The contains method in the if statement is the recursion to check the nexts 
			if ( ( aValue.equals( value ) ) || ( link.contains(aValue ) ) )
			{
				return true;
			}
		}
		return false;
	}
	
/*	public RecList<Type> getLink()
	{
		return link; /// MAG NIET
	}*/
	
	public Type getValue()
	{
		return value;
	}
	
	public void remove( Type object )
	{
		/* Als lijst niet leeg is, kijk, 
		   is de te remove object in de 
		   huidige lijs? zowel, verwijder 
		   het, anders check recursief met volgende */
		
		if( ( !isEmpty() ) && ( contains(object) ) )
		{
			if( value.equals( object ) )
			{
				value = link.value;
				link = link.link;			
			}
			else
			{
				if( link != null )
				{
					link.remove( object );
				}
			}
		}
	}
	
	public String toString()
	{
		/* if isEmpty then (?) "_" else (:) do the rest. toString can be avoided */
		return isEmpty() ? "_" : value + "," + link;
		//return isEmpty() ? "_" : value + "," + link.toString();
	}

	@Override
	public Iterator<Type> iterator()
	{
		return new ReclistIterator<Type>( this );
	}
	
	////////////////////////
	class ReclistIterator<T> implements Iterator<T>
	{
		private RecList<T> recList;

		public ReclistIterator( RecList<T> recList )
		{
			this.recList = recList;
		}

		public boolean hasNext()
		{
			return isEmpty() ? false : true;
		}

		@Override
		public T next()
		{
			if ( hasNext() )
			{
				RecList<T> tmpRecList = recList;
				recList = recList.link;
				return tmpRecList.link.getValue();
			}
			else
			{
				return new RecList<T>().getValue();
			}
		}

		@Override
		public void remove()
		{
			// TODO Auto-generated method stub
		}
	}
}
