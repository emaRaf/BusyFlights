package lz.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lz.model.Card;

@Component
public class TodoDao implements Dao<Card> {

    private final List<Card> todoList = new ArrayList<>();

    @Override
    public Optional<Card> get(int id) {
	return Optional.ofNullable(todoList.get(id));
    }

    @Override
    public Collection<Card> getAll() {
	return todoList.stream().filter(Objects::nonNull)
		.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public int save(Card todo) {
	todoList.add(todo);
	final int index = todoList.size() - 1;
	// todo.setId(index);
	return index;
    }

    @Override
    public void update(Card todo) {
	// todoList.set(todo.getId(), todo);
    }

    @Override
    public void delete(Card todo) {
//	todoList.set(todo.getId(), null);
    }
}
