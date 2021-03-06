package cn.junone.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
@Table(name = "JOF_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String username;
    private String password;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "last_time")
    private Timestamp lastTime;
    private String nickname;
    private String random;
}
