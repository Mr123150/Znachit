package ru.mr123150.znachit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ProfileActivity extends Activity {
	
	String[] groups = new String[] { "Зима", "Весна", "Лето", "Осень" };

	String[] winterMonths = new String[] { "Декабрь", "Январь", "Февраль" };
	String[] springMonths = new String[] { "Март", "Апрель", "Май" };
	String[] summerMonths = new String[] { "Июнь", "Июль", "Август" };
	String[] autumnMonths = new String[] { "Сентябрь", "Октябрь", "Ноябрь" };

	// коллекция для групп
	ArrayList<Map<String, String>> groupData;

	// коллекция для элементов одной группы
	ArrayList<Map<String, String>> childDataItem;

	// общая коллекция для коллекций элементов
	ArrayList<ArrayList<Map<String, String>>> childData;
	// в итоге получится childData = ArrayList<childDataItem>

	// список атрибутов группы или элемента
	Map<String, String> m;

	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Show the Up button in the action bar.
		setupActionBar();
		
		groupData = new ArrayList<Map<String, String>>();
		for (String group : groups) {
			// заполняем список атрибутов для каждой группы
			m = new HashMap<String, String>();
			m.put("groupName", group); // время года
			groupData.add(m);
		}

		// список атрибутов групп для чтения
		String groupFrom[] = new String[] { "groupName" };
		// список ID view-элементов, в которые будет помещены аттрибуты групп
		int groupTo[] = new int[] { android.R.id.text1 };

		// создаем коллекцию для коллекций элементов
		childData = new ArrayList<ArrayList<Map<String, String>>>();

		// создаем коллекцию элементов для первой группы
		childDataItem = new ArrayList<Map<String, String>>();
		// заполняем список аттрибутов для каждого элемента
		for (String month : winterMonths) {
			m = new HashMap<String, String>();
			m.put("monthName", month); // название месяца
			childDataItem.add(m);
		}
		// добавляем в коллекцию коллекций
		childData.add(childDataItem);

		// создаем коллекцию элементов для второй группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : springMonths) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// создаем коллекцию элементов для третьей группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : summerMonths) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// создаем коллекцию элементов для четвертой группы
		childDataItem = new ArrayList<Map<String, String>>();
		for (String month : autumnMonths) {
			m = new HashMap<String, String>();
			m.put("monthName", month);
			childDataItem.add(m);
		}
		childData.add(childDataItem);

		// список аттрибутов элементов для чтения
		String childFrom[] = new String[] { "monthName" };
		// список ID view-элементов, в которые будет помещены аттрибуты
		// элементов
		int childTo[] = new int[] { android.R.id.text1 };

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		expListView = (ExpandableListView) findViewById(R.id.profile_list);
		expListView.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
