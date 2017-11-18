package com.pausa.pranzo.core.group;

import java.util.UUID;

public interface GroupRepository
{
  Group byId(UUID id);
}
