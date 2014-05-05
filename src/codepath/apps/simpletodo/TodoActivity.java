package codepath.apps.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {
	private final int REQUEST_CODE = 20;
	
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);

		lvItems = (ListView) findViewById(R.id.lvItems);
		items = new ArrayList<String>();
		itemsAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		
		readItems();
		setupListViewListener();
	}
	
	public void addTodoItem(View v) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		itemsAdapter.add(etNewItem.getText().toString());
		etNewItem.setText("");
	}
	
	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> aView, 
					View item, int pos, long id) {
				itemsAdapter.remove(itemsAdapter.getItem(pos));
				itemsAdapter.notifyDataSetChanged();
				saveItems();
				return true;
			}
		});
		
		lvItems.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> aView,
				View item, int pos, long id) {
				
				String todoItem = itemsAdapter.getItem(pos);
				   
				Intent editIntent = new Intent(TodoActivity.this, EditItemActivity.class);
				editIntent.putExtra("item", todoItem);
				editIntent.putExtra("position", pos);
				startActivityForResult(editIntent, REQUEST_CODE);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			String todoItem = data.getExtras().getString("todoItem");
			int pos = data.getExtras().getInt("position");
			itemsAdapter.remove(itemsAdapter.getItem(pos));     
			itemsAdapter.insert(todoItem, pos);
			itemsAdapter.notifyDataSetChanged();
			saveItems();
		}
	}
	
	private void readItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
			e.printStackTrace();
		}
			
	}
	
	private void saveItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
