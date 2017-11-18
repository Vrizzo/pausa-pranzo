package com.pausa.pranzo.core.usecase;

import com.pausa.pranzo.core.event.Event;
import com.pausa.pranzo.core.event.EventRepository;
import com.pausa.pranzo.core.event.EventType;
import com.pausa.pranzo.core.group.Group;
import com.pausa.pranzo.core.group.GroupRepository;
import com.pausa.pranzo.core.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class SendProposalToGroup
{

  private final GroupRepository groupRepository;
  private final EventRepository eventRepository;

  public SendProposalToGroup(GroupRepository groupRepository, EventRepository eventRepository)
  {
    this.groupRepository = groupRepository;
    this.eventRepository = eventRepository;
  }

  public void send(User user, UUID placeID)
  {
    final UUID groupId = user.getGroups().iterator().next();
    final Group group = groupRepository.byId(groupId);
    group.getUserIds().stream().map(userId -> toEvent(userId, placeID)).forEach(eventRepository::publish);
  }

  private Event toEvent(UUID userId, UUID placeId)
  {
    return new Event<>(LocalDateTime.now(), EventType.PLACE_PROPOSAL, new PlaceProposal(userId, placeId));
  }

  public static class PlaceProposal
  {
    private final UUID userId;
    private final UUID placeId;

    public PlaceProposal(UUID userId, UUID placeId)
    {
      this.userId = userId;
      this.placeId = placeId;
    }

    public UUID getUserId()
    {
      return userId;
    }

    public UUID getPlaceId()
    {
      return placeId;
    }
  }
}
