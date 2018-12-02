package iq.kurd.com.util.msg;

import java.util.ArrayList;
import java.util.List;

import iq.kurd.com.util.format.StringUtil;

/**
 * <pre>
 * FileName      : DialogBox.java
* Package       : iq.kurd.pt
 *
 * System Name   : 
 * Business Name : 
 * Description   : 
 *
 *
 * -- Modification Information --
 *   
 *  update date        updater       description
 *  ------------	  --------		---------------------------
 *  2016. 12. 19.		root         First Create
 *
 * </pre>
 * @author : root
 * @date   : 2016. 12. 19.
 *
 */
public class DialogBox {
	
	private Integer numOfButtons;
	private List<Button> buttonList;
	private String destUrl;
	private String focusTo;
	private String func;
	private String message;
	private String textAlign;
	private String title;
	private StringBuffer options;
	
	public DialogBox(String message) {
		this.message = message; 
	}
	
	public DialogBox setButtons(Integer numOfButtons) {
		this.numOfButtons = numOfButtons;
		return this;
	}
	
	public DialogBox setButtons(List<Button> buttonList) {
		this.buttonList = buttonList;
		return this;
	}
	
	public DialogBox addButton(Button button) {
		if (buttonList == null) {
			buttonList = new ArrayList<Button>();
		}
		buttonList.add(button);
		return this;
	}
	
	public String getDestUrl() {
		return destUrl;
	}
	
	public DialogBox setDestUrl(String destUrl) {
		this.destUrl = destUrl;
		return this;
	}
	
	public String getFocusTo() {
		return focusTo;
	}
	
	public DialogBox setFocusTo(String focusTo) {
		this.focusTo = focusTo;
		return this;
	}
	
	public String getFunc() {
		return func;
	}
	
	public DialogBox setFunc(String func) {
		this.func = func;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public DialogBox setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public String getTextAlign() {
		return textAlign;
	}
	
	public DialogBox setTextAlign(String textAlign) {
		this.textAlign = textAlign;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public DialogBox setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private void appendOption(String name, String value) {
		if (options.length() > 0) options.append(',');
		options.append(quotes(name) + ":" + value);
	}
	
	private String quotes(String string) {
		return "'" + string + "'";
	}
	
	public String getOptions() {
		options = new StringBuffer();
		if (numOfButtons != null) {
			appendOption("buttons", String.valueOf(numOfButtons));
		} else if (buttonList != null) {
			StringBuffer buttons = new StringBuffer();
			for (Button button : buttonList) {
				buttons.append("{");
				String buttonId = button.getId();
				String buttonOnclick = button.getOnclick();
				String buttonValue = button.getValue();
				if (buttonId != null && buttonId.length() > 0) {
					buttons.append(quotes("id") + ":" + quotes(buttonId) + ",");
				}
				if (buttonOnclick != null) {
					if (buttonOnclick.trim().endsWith("()")) {
						buttonOnclick = quotes(buttonOnclick);
					}
					buttons.append(quotes("onclick") + ":" + buttonOnclick + ",");
				}
				if (buttonValue != null) {
					buttons.append(quotes("value") + ":" + quotes(buttonValue) + ",");
				}
				if (buttons.toString().endsWith(",")) {
					buttons.setLength(buttons.length() - 1);
				}
				buttons.append("},");
			}
			if (buttons.toString().endsWith(",")) {
				buttons.setLength(buttons.length() - 1);
			}
			appendOption("buttons", "[" + buttons.toString() + "]");
		}
		if (destUrl != null) {
			appendOption("destUrl", quotes(destUrl));
		}
		if (func != null) {
			appendOption("func", quotes(func));
		}
		if (textAlign != null) {
			appendOption("textAlign", quotes(textAlign));
		}
		if (title != null) {
			appendOption("title", quotes(title));
		}
		return options.toString();
	}

	@Override
	public String toString() {
		String message = this.message;
		String options = getOptions();
		
		if (StringUtil.isEmpty(message)) {
			message = "";
		}
		if (options.length() == 0) {
			return quotes(message) + "," + "null";
		} else {
			return quotes(message) + "," + "{" + options + "}";
		}
	}
	
	public static class Button {
		
		private String id;
		private String onclick;
		private String value;
		
		public Button() {}
		
		public Button(String value) {
			this.value = value;
		}
		
		public String getId() {
			return id;
		}
		
		public Button setId(String id) {
			this.id = id;
			return this;
		}
		
		public String getOnclick() {
			return onclick;
		}
		
		public Button setOnclick(String onclick) {
			this.onclick = onclick;
			return this;
		}
		
		public String getValue() {
			return value;
		}
		
		public Button setValue(String value) {
			this.value = value;
			return this;
		}
	}
}
