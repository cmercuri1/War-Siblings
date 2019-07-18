package unused;

import event_classes.EventObject;

public class EventAttributeGet {
	protected Object requester;
	protected String attributeName;

	public EventAttributeGet(Object requester, String attributeName) {
		this.requester = requester;
		this.attributeName = attributeName;
	}

	public Object getRequester() {
		return requester;
	}

	public String getAttributeName() {
		return this.attributeName;
	}
}
