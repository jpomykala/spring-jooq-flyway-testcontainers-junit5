package me.jpomykala.springjooqtestcontainersmysql;

import me.jpomykala.generated.tables.records.PostRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.jpomykala.generated.tables.Post.POST;

@Repository
public class PostRepository {

  private final DSLContext context;

  @Autowired
  public PostRepository(DSLContext context) {
    this.context = context;
  }

  @NotNull
  public List<PostRecord> findAll() {
    return context.select()
            .from(POST)
            .fetchInto(PostRecord.class);
  }

  @NotNull
  public PostRecord store(@NotNull PostRecord postRecord) {
    context.attach(postRecord);
    postRecord.store();
    return postRecord.copy();
  }
}
