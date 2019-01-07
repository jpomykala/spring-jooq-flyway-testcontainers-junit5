package me.jpomykala.springjooqtestcontainersmysql;

import me.jpomykala.generated.tables.Post;
import me.jpomykala.generated.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Sql("classpath:db/migration/V1__Init.sql")
public class PostRepositoryJUnit5Test extends AbstractTestConfiguration {

  @Autowired
  private DSLContext context;

  private PostRepository postRepository;

  @BeforeEach
  public void setUp() {
    assertThat(context).isNotNull();
    postRepository = new PostRepository(context);
  }

  @Test
  public void proof_of_concept_test() {
    //given
    PostRecord postRecord = Post.POST.newRecord();
    String givenAuthorName = "Jakub";
    postRecord.setAuthorName(givenAuthorName);
    postRecord.setTitle("Hey, I just met you and this is crazy");
    postRecord.setBody("But here\'s my number, so call me maybe");
    Timestamp created = Timestamp.valueOf(LocalDateTime.now());
    postRecord.setCreated(created);
    postRepository.store(postRecord);


    //when
    List<PostRecord> all = postRepository.findAll();

    //then
    assertThat(all).hasSize(1);
    assertThat(all.get(0)).isNotNull();
    String authorName = all.get(0).getAuthorName();
    assertThat(authorName).isEqualTo(givenAuthorName);
  }

}
