package com.pausa.pranzo.core.user;

import java.util.Collection;
import java.util.UUID;

/**
 * Simple user entity
 */
public class User
{

  private final UUID id;
  private final String name;
  private final String username;
  private final Collection<UUID> groups;

  User(Builder builder)
  {
    this.id = builder.id;
    this.name = builder.name;
    this.username = builder.username;
    this.groups = builder.groups;
  }

  public UUID getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getUsername()
  {
    return username;
  }

  public Collection<UUID> getGroups()
  {
    return groups;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return id.equals(user.id);
  }

  @Override
  public int hashCode()
  {
    return id.hashCode();
  }

  @Override
  public String toString()
  {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", groups=" + groups +
            '}';
  }

  public static final class Builder
  {

    private UUID id;
    private String name;
    private String username;
    public Collection<UUID> groups;

    public Builder withId(UUID id)
    {
      this.id = id;
      return this;
    }

    public Builder withName(String name)
    {
      this.name = name;
      return this;
    }

    public Builder withUsername(String username)
    {
      this.username = username;
      return this;
    }

    public Builder withGroups(Collection<UUID> groups)
    {
      this.groups = groups;
      return this;
    }

    public User build()
    {
      return new User(this);
    }
  }

}
