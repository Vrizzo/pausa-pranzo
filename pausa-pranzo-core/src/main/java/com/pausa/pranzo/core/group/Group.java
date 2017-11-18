package com.pausa.pranzo.core.group;

import java.util.Collection;
import java.util.UUID;

public class Group
{
  private final UUID id;
  private final Collection<UUID> userIds;

  public Group(UUID id, Collection<UUID> userIds)
  {
    this.id = id;
    this.userIds = userIds;
  }

  public UUID getId()
  {
    return id;
  }

  public Collection<UUID> getUserIds()
  {
    return userIds;
  }
}
