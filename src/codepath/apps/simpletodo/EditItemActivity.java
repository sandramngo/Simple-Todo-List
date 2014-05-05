package codepath.apps.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	EditText etEditItem;
	int position;
	int code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		
		String todoItem = getIntent().getStringExtra("item");
		code = getIntent().getIntExtra("code", 0);
		position = getIntent().getIntExtra("position", -1);
		
		etEditItem = (EditText) findViewById(R.id.etEditItem);
		etEditItem.setText(todoItem);

	}
	
	public void saveTodoItem(View v) {
		String newTodoItem = etEditItem.getText().toString();
		Intent data = new Intent();
		data.putExtra("todoItem", newTodoItem);
		data.putExtra("position", position);
		setResult(RESULT_OK, data);
		finish();
	}

}
