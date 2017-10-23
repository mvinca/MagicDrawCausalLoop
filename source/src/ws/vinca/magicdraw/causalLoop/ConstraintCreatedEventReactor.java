package ws.vinca.magicdraw.causalLoop;

public class ConstraintCreatedEventReactor implements IEventReactor
{
	@Override
	public void React(ICausalEvent e)
	{
		if( e instanceof ConstraintCreatedEvent )
		{
			ConstraintCreatedEvent cce = (ConstraintCreatedEvent) e;
			cce.getLink().setName("C");
		}
	}

}
