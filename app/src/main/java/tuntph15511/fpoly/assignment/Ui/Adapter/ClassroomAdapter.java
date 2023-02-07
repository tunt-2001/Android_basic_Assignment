package tuntph15511.fpoly.assignment.Ui.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import tuntph15511.fpoly.assignment.Dao.ClassroomDao;
import tuntph15511.fpoly.assignment.Model.Classroom;
import tuntph15511.fpoly.assignment.R;

public class ClassroomAdapter extends BaseAdapter {
        private List<Classroom> data;

        private ClassroomDao dao;
        private LinearLayout contentItemClassroom;

        private TextView id;
        private TextView codeClassroom;
        private TextView nameClassroom;
        private Callback callback;
        public interface Callback{
            void delete(Classroom classroom);
        }
        public ClassroomAdapter(List<Classroom> data , Context context , Callback callback) {
            this.data = data;
            dao = new ClassroomDao(context);
            this.callback = callback;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return data.get(i).getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewClassroom;

            if (view == null) {
                viewClassroom = View.inflate(viewGroup.getContext(), R.layout.activity_list_class, null);
            } else {
                viewClassroom = view;
            }

            contentItemClassroom = (LinearLayout) View.inflate(viewGroup.getContext(), R.layout.activity_list_class, null);
            id = (TextView) viewClassroom.findViewById(R.id.id);
            codeClassroom = (TextView) viewClassroom.findViewById(R.id.codeClassroom);
            nameClassroom = (TextView) viewClassroom.findViewById(R.id.nameClassroom);

            Classroom classroom = (Classroom) getItem(i);
            id.setText(String.valueOf(classroom.getId()));
            codeClassroom.setText(classroom.getCodeClassroom());
            nameClassroom.setText(classroom.getNameClassroom());

            contentItemClassroom.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    callback.delete(classroom);
                    return true;
                }
            });

            return viewClassroom;
        }
    }


