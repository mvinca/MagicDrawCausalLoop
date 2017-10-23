package ws.vinca.magicdraw.causalLoop;

import java.util.List;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.SymbolElementMap;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;

public class MagnitudeEventReactor implements IEventReactor
{
	@SuppressWarnings("deprecation")
	@Override
	public void React(ICausalEvent e)
	{
		if( e instanceof MagnitudeEvent )
		{
			MagnitudeEvent event = (MagnitudeEvent) e;
			Project currentProject = Project.getProject(event.getLink());
			SymbolElementMap symbolByElement = currentProject.getSymbolElementMap();
			currentProject.getDiagrams().stream().forEach((diagram) ->
			{
				List<PresentationElement> views = symbolByElement.getAllPresentationElements(event.getLink(), diagram);
				views.stream().filter((de) -> de instanceof PathElement).forEach((diagramElement) ->
				{
					((PathElement) diagramElement).setLineWidth(ConvertToLineWidth(event.getEnumerationValue().getName()));
				});
			});
		}
	}

	private int ConvertToLineWidth(String tagValue)
	{
		int width = 1;
		switch( tagValue )
		{
			case "Above":
				width = 2;
				break;
			case "Extreme":
				width = 3;
				break;
		}
		return width;
	}
}
