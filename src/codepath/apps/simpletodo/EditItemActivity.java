package codepath.apps.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
