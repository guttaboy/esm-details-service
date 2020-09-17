package com.wood.esm.detailservice.profile.web.response;

import java.util.List;

import com.wood.esm.detailservice.profile.DTO.ContactDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactGetResponse {

	private List<ContactDTO> contactDTOs;

}
