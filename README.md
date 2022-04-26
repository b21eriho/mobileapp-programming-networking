
# Report



```
public void onPostExecute(String json) {
        Gson gson = new Gson();

        Type type = new TypeToken<List<Mountain>>() {}.getType();
        mountainList = gson.fromJson(json, type);

        mountainNameList = new ArrayList<String>();

        for (int i = 0; i < mountainList.size(); i++){
            mountainNameList.add(mountainList.get(i).toString());
        }

        mountainAdapter.setMountains(mountainNameList);
        mountainAdapter.notifyDataSetChanged();
    }
```

![](Screenshot.png)
