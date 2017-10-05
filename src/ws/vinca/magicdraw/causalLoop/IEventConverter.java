package ws.vinca.magicdraw.causalLoop;

import java.beans.PropertyChangeEvent;
import java.util.List;

public interface IEventConverter {
	List<ICausalEvent> ConvertEvent( PropertyChangeEvent event );
}
