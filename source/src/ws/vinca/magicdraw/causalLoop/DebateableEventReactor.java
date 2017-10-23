package ws.vinca.magicdraw.causalLoop;

public class DebateableEventReactor implements IEventReactor {

	private static String DEBATE_PREFIX = "? - ";
	@Override
	public void React(ICausalEvent e) 
	{
		if( e instanceof DebateableEvent )
		{
			DebateableEvent de = (DebateableEvent)e;
			if( de.isDebateable() )
			{
				if( de.getLink().getName().length() == 0 )
				{
					de.getLink().setName( "?" );
				}
				else if( !de.getLink().getName().equals("?") )
				{
					de.getLink().setName(DEBATE_PREFIX + de.getLink().getName() );
				}
			}
			else
			{
				if( de.getLink().getName().startsWith(DEBATE_PREFIX) )
				{
					de.getLink().setName(de.getLink().getName().substring(DEBATE_PREFIX.length()));
				}
				else
				{
					de.getLink().setName( "" );
				}
			}
		}
	}

}
