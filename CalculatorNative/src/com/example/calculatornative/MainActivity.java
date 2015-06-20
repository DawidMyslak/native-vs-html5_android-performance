package com.example.calculatornative;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	String input = "";
	boolean reset = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonEqual = (Button) findViewById(R.id.buttonequal);
		Button buttonBack = (Button) findViewById(R.id.buttonback);
		Button buttonClear = (Button) findViewById(R.id.buttonclear);

		buttonEqual.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				input = calculate(input);
				refresh();
				reset = true;
			}
		});

		buttonBack.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				back();
			}
		});

		buttonClear.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				clear();
			}
		});
	}

	void refresh() {
		EditText expression = (EditText) findViewById(R.id.expression);
		expression.setText(input);
	}

	public void display(View view) {
		Button button = (Button) view;
		String character = button.getText().toString();
		if (reset) {
			input = "";
			reset = false;
		}
		input += character;
		refresh();
	}

	void back() {
		if (!input.equals(""))
			input = input.substring(0, input.length() - 1);
		refresh();
	}

	void clear() {
		input = "";
		refresh();
	}

	Stack<String> convert(String expression) {
		Map<String, Integer> priorities = new HashMap<String, Integer>();
		priorities.put("(", 0);
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("×", 2);
		priorities.put("÷", 2);
		Stack<String> stack = new Stack<String>();
		Stack<String> output = new Stack<String>();

		for (int i = 0; i < expression.length(); i++) {
			String character = Character.toString(expression.charAt(i));

			if (character.equals("(")) {
				stack.push(character);
			} else if (character.equals(")")) {
				String stackCharacter = "";
				while (!(stackCharacter = stack.pop()).equals("(")) {
					output.push(stackCharacter);
				}
			} else if (character.equals("+") || character.equals("-")
					|| character.equals("×") || character.equals("÷")) {
				while (stack.size() > 0 && priorities.get(stack.elementAt(stack.size() - 1)) >= priorities
						.get(character)) {
					output.push(stack.pop());
				}
				stack.push(character);
			} else {
				int j = 0;
				for (j = i + 1; j < expression.length(); j++) {
					String nextCharacter = Character.toString(expression
							.charAt(j));

					if (nextCharacter.equals("(") || nextCharacter.equals(")")
							|| nextCharacter.equals("+")
							|| nextCharacter.equals("-")
							|| nextCharacter.equals("×")
							|| nextCharacter.equals("÷")) {
						break;
					}
					character += nextCharacter;
				}
				i = j - 1;
				output.push(character);
			}
		}
		while (stack.size() != 0) {
			output.push(stack.pop());
		}
		
		return output;
	}

	String calculate(String expression) {
		Stack<String> expressionStack = convert(expression);
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < expressionStack.size(); i++) {
			String character = expressionStack.elementAt(i);

			if (character.equals("+") || character.equals("-")
					|| character.equals("×") || character.equals("÷")) {
				Float a = Float.parseFloat(stack.pop());
				Float b = Float.parseFloat(stack.pop());
				if (character.equals("+")) {
					stack.push(Float.toString(b + a));
				} else if (character.equals("-")) {
					stack.push(Float.toString(b - a));
				} else if (character.equals("×")) {
					stack.push(Float.toString(b * a));
				} else if (character.equals("÷")) {
					stack.push(Float.toString(b / a));
				}
			} else {
				stack.push(character);
			}
		}
		return stack.pop().toString();
	}
}
